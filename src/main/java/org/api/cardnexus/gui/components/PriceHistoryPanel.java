package org.api.cardnexus.gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.api.cardnexus.model.AbstractProduct;
import org.api.cardnexus.model.History;
import org.api.cardnexus.model.enums.EnumMarketPlace;
import org.api.cardnexus.model.requests.HistoryRequest;
import org.api.cardnexus.services.PricesService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

public class PriceHistoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    protected transient Logger logger = LogManager.getLogger(getClass());
    private ChartPanel chartPanel;
    private AbstractProduct product;
    
    public PriceHistoryPanel() {
	setLayout(new BorderLayout());
	
	chartPanel = new ChartPanel(null, true);
	add(chartPanel,BorderLayout.CENTER);
	
	chartPanel.addMouseWheelListener(mwe -> {
		if (mwe.getWheelRotation() > 0) {
			chartPanel.zoomOutDomain(0.5, 0.5);

		} else if (mwe.getWheelRotation() < 0) {
			chartPanel.zoomInDomain(1.5, 1.5);
		}
	});
	
	 chartPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()/2));
    }
    
    
    
    public void init(AbstractProduct p) {
	
	if(p==null)
	    return;
	
	this.product = p;
	    
	    var wk = new SwingWorker<List<History>, Void>() {

		@Override
		protected List<History> doInBackground() throws Exception {
		    return new PricesService().getHistoryPrice(HistoryRequest.create().setIdProduct(p.getId()).setFrom(LocalDate.now(ZoneId.systemDefault()).minusDays(364)));
		}
		
		@Override
		protected void done() {
		    
		    try {
			createChart(createDataSet(get()));
		    } catch (InterruptedException _) {
			Thread.currentThread().interrupt();
		    } catch (ExecutionException e) {
			logger.error(e);
		    }
		}
	    };
	    wk.execute();
    }
    
    private TimeSeriesCollection createDataSet(List<History> res) {
	var dataset = new TimeSeriesCollection();
	
	for(var f : res.stream().map(h->h.finish()).distinct().toList())
	{
        	for(var p : new EnumMarketPlace[] {EnumMarketPlace.cardmarket, EnumMarketPlace.tcgplayer})
        	{
        	    	var series1 = new TimeSeries(p.name() + " " + f );
                	res.stream().filter(h->h.marketplace()==p && h.finish()==f).forEach(h-> series1.add(new Day(h.date()), h.marketValue()));
                	dataset.addSeries(series1);
        	}
	}
        return dataset;
    }



    private void createChart(XYDataset dataset) {
        var chart = ChartFactory.createTimeSeriesChart(
            product.getName(),      // chart title
            "Date",                      // y axis label
            "Value",                      // y axis label
            dataset,                  // data
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
                
        chartPanel.setChart(chart);
    }
    

}
