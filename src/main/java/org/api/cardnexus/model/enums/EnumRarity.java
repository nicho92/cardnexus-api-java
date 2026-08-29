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
    @SerializedName(alternate = {"super_rare"}, value = "Super Rare") super_rare,
    @SerializedName(value = "Legendary") legendary,
    @SerializedName(value = "Epic") epic,
    @SerializedName(value = "Iconic") iconic,
    @SerializedName(value = "Enchanted") enchanted,
    
    //POKEMON
    
    @SerializedName(value = "DOUBLE_RARE") double_rare,
    @SerializedName(alternate= {"Rare Art"}, value = "ILLUSTRATION_RARE") illustration_rare,
    @SerializedName(alternate= {"Ultra Rare"}, value = "ULTRA_RARE") ultra_rare,
    @SerializedName(value = "HYPER_RARE") hyper_rare,
    @SerializedName(value = "SPECIAL_ILLUSTRATION_RARE") special_illustration_rare,
    @SerializedName(value = "HOLO_RARE") holo_rare,
    @SerializedName(value = "BLACK_WHITE_RARE") black_white_rare,
    @SerializedName(alternate = {"secret_rare"}, value = "SECRET_RARE") secret_rare,
    @SerializedName(value = "ACE_SPEC_RARE") ac_espec_rare,
    @SerializedName(value = "RARE_PRISM_STAR") rare_prism_star,
    
    //FLESH AND BLOOD
    @SerializedName(value = "Marvel") marvel,
    @SerializedName(value = "Majestic") majestic,
    
    //ONE PIECE
    leader,
    @SerializedName(value = "treasure_rare") treasure_rare, 
    
    //SORCERY
    @SerializedName(value="Unique") unique,
    @SerializedName(value="Exceptional") exceptional,
    @SerializedName(value="Ordinary") ordinary,
    @SerializedName(value="Elite") elite,
    
    //NARUTO
    @SerializedName(value="Secret Variant") secret_variant,
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
    @SerializedName(value="Collector Super Rare") collector_super_rare,
    @SerializedName(value="Collector Promo Rare") collector_promo_rare,
    @SerializedName(value="Collector Ultra Rare") collector_ultra_rare,
    @SerializedName(value="Promotional Rare") promo_rare,
    
    
    //GUNDAM
    rare_plus,
    legend_rare_plus,
    legend_rare,
    legend_rare_double_plus,
    common_plus,
    uncommon_plus
    
}
