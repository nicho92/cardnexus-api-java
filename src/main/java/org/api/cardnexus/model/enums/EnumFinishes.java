package org.api.cardnexus.model.enums;

import com.google.gson.annotations.SerializedName;

public enum EnumFinishes {

    Standard,
    Foil,
    Rainbow,
    Gold,
   @SerializedName(value="Rainbow Foil") Rainbow_Foil,
   @SerializedName(value="Cold Foil") Cold_Foil,
   @SerializedName(value="Gold Foil") Gold_Foil,
    Etched,
    Signed,
    @SerializedName(value="Reverse Holo") Reverse_Holo,
    @SerializedName(value="Blast Foil Rainbow") Blast_Foil_Rainbow,
    @SerializedName(value="Bubbles Foil") Bubbles_Foil,
    @SerializedName(value="Cracked Ice Foil") Cracked_Ice_Foil,
    @SerializedName(value="Galaxy Foil") Galaxy_Foil,
    @SerializedName(value="Glitter Foil") Glitter_Foil,
    @SerializedName(value="Multi Sideline Foil") Multi_Sideline_Foil,
    @SerializedName(value="Rainbow Solid Texture Stamp") Rainbow_Solid_Texture_Stamp,
    @SerializedName(value="Star Foil") Star_Foil,
    @SerializedName(value="Surge Foil") Surge_Foil,
    @SerializedName(value="Full Art Gold Sign") Full_Art_Gold_Sign,
    @SerializedName(value="Depth Lenticular") Depth_Lenticular,
    @SerializedName(value="Flip Lenticular") Flip_Lenticular,
    Lenticular,
    Zarimoth,
    Serialized,
    @SerializedName(value="Serialized Rose Gold") Serialized_Rose_Gold,
    @SerializedName(value="Serialized Gold") Serialized_Gold,
    Holographic,
    Embossed,
    Holofoil
}
