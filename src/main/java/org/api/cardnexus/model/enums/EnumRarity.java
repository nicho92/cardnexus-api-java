package org.api.cardnexus.model.enums;

import com.google.gson.annotations.SerializedName;

public enum EnumRarity {
    
    //COMMON
    @SerializedName(alternate = {"Common","COMMON"}, value = "common") common, 
    @SerializedName(alternate = {"Uncommon","UNCOMMON"}, value = "uncommon") uncommon, 
    @SerializedName(alternate = {"Rare","RARE"}, value = "rare") rare,
    @SerializedName(alternate = {"Promo"}, value = "PROMO") promo,
    @SerializedName(alternate = {"Special"}, value = "special") special,

    
   //MTG
    mythic,
    
    //LORCANA
    @SerializedName(alternate = {"super_rare"}, value = "Super Rare") superrare,
    @SerializedName(value = "Legendary") legendary,
    @SerializedName(value = "Epic") epic,
    @SerializedName(value = "Iconic") iconic,
    @SerializedName(value = "Enchanted") enchanted,
    
    //POKEMON
    
    @SerializedName(value = "DOUBLE_RARE") doublerare,
    @SerializedName(alternate= {"Rare Art"}, value = "ILLUSTRATION_RARE") illustrationrare,
    @SerializedName(alternate= {"Ultra Rare"}, value = "ULTRA_RARE") ultrarare,
    @SerializedName(value = "HYPER_RARE") hyperrare,
    @SerializedName(value = "SPECIAL_ILLUSTRATION_RARE") specialillustrationrare,
    @SerializedName(value = "HOLO_RARE") holorare,
    @SerializedName(value = "BLACK_WHITE_RARE") blackwhiterare,
    @SerializedName(alternate = {"secret_rare"}, value = "SECRET_RARE") secretrare,
    @SerializedName(value = "ACE_SPEC_RARE") acespecrare,
    @SerializedName(value = "RARE_PRISM_STAR") rareprismstar,
    
    //FLESH AND BLOOD
    @SerializedName(value = "Marvel") marvel,
    @SerializedName(value = "Majestic") majestic,
    
    //ONE PIECE
    leader,
    @SerializedName(value = "treasure_rare") treasurerare, 
    
    //SORCERY
    @SerializedName(value="Unique") unique,
    @SerializedName(value="Exceptional") exceptional,
    @SerializedName(value="Ordinary") ordinary,
    @SerializedName(value="Elite") elite,
    
    //NARUTO
    @SerializedName(value="Secret Variant") secretvariant,
    @SerializedName(value="Mythos") mythos,
    @SerializedName(value="Secret") secret,
    
    //RIFTBOUND
    @SerializedName(value="Showcase") showcase,
    
    //DRAGONBALL
    none,
    special_rare,
    special_leader_rare,
    son_gohan_rare,
    god_rare,
    
    //GRAND ARCHIVE TCG
    @SerializedName(value="Collector Super Rare") collectorsuperrare,
    @SerializedName(value="Collector Promo Rare") collectorpromorare,
    @SerializedName(value="Collector Ultra Rare") collectorultrarare,
    @SerializedName(value="Promotional Rare") promorare,
    
    
    //GUNDAM
    rare_plus,
    legend_rare_plus,
    legend_rare,
    legend_rare_double_plus,
    common_plus,
    uncommon_plus
    
}
