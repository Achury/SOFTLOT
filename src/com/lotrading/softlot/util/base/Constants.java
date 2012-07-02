package com.lotrading.softlot.util.base;

public class Constants {

	//constanst para BD REAL
	/**
	 * Constantes para los master.
	 *//*
	public static int MASTER_DEPARTMENT = 1;
	public static int MASTER_PHONE_TYPE = 2;
	public static int MASTER_SHIPPING_TYPE = 3;
	public static int MASTER_COUNTRY = 4;
	public static int MASTER_PROVINCE = 5;
	public static int MASTER_INVOICE_TYPE = 99;
	public static int MASTER_CLIENT_STATUS = 7;
	public static int MASTER_LANGUAGE = 8;
	public static int MASTER_TRUCK_COMPANY = 9;
	public static int MASTER_PAYMENT_TERMS = 11;
	public static int MASTER_COURIER = 12;
	public static int MASTER_CLIENT_ORDER_STATUS = 99;
	public static int MASTER_CARRIER_TYPE = 99;
	public static int MASTER_INCOTERM = 99;
	public static int MASTER_MODULE_TYPE = 99;
	public static int MASTER_REGION = 13;
	
	
	*//**
	 * Constantes para los master values.
	 *//*
	public static int MASTER_VALUE_PHONE_TYPE_TEL = 14;
	public static int MASTER_VALUE_PHONE_TYPE_FAX = 16;
	public static int MASTER_VALUE_DEPARTMENT_EVERYONE = 1;
	public static int MASTER_VALUE_DEPARTMENT_RM = 2;
	public static int MASTER_VALUE_DEPARTMENT_IP = 3;
	public static int MASTER_VALUE_DEPARTMENT_FF = 4;
	public static int MASTER_VALUE_SHIPPING_TYPE_AIR = 393;
	public static int MASTER_VALUE_SHIPPING_TYPE_OCEAN = 394;
	public static int MASTER_VALUE_CLIENT_ORDER_MODULE = 410;
	public static int MASTER_VALUE_REGION_USA = 403;
	public static int MASTER_VALUE_REGION_GER = 404;
	public static int MASTER_VALUE_REGION_COL = 405;
	public static int MASTER_VALUE_STATUS_UNCONFIRMED = 384;
	public static int MASTER_VALUE_STATUS_SCHEDULED = 385;
	public static int MASTER_VALUE_STATUS_IN_TRANSIT = 386;
	public static int MASTER_VALUE_TYPE_ORIGINAL = 412;
	public static int MASTER_VALUE_TYPE_COPY = 411;
	public static int MASTER_VALUE_RATE_TYPE_40 = 419;
	public static int MASTER_VALUE_RATE_TYPE_20 = 418;
	public static int MASTER_VALUE_RATE_TYPE_LCL = 417;
	public static int MASTER_VALUE_RATE_TYPE_AIR = 416;
	*/
	
	
	
	
	//constanst para BD DESARROLLO
	
	/**
	 *CONSTANTES PARA NOMBRE DE CONVERTERS 
	 **/
	public static String CONVERTER_CURRENCY_DOLLAR = "currencyDollarConverter";
	public static String CONVERTER_CURRENCY_EURO = "currencyEuroConverter";
	
	/**
	 * Constantes para los master.
	 */
	public static int MASTER_DEPARTMENT = 1;
	public static int MASTER_PHONE_TYPE = 2;
	//public static int MASTER_SHIPPING_TYPE = 3;
	public static int MASTER_COUNTRY = 4;
	public static int MASTER_PROVINCE = 5;
	public static int MASTER_INVOICE_TYPE = 6;
	public static int MASTER_CLIENT_STATUS = 7;
	public static int MASTER_LANGUAGE = 8;
	public static int MASTER_TRUCK_COMPANY = 9;
	public static int MASTER_PAYMENT_TERMS = 11;
	public static int MASTER_COURIER = 12;
	public static int MASTER_CLIENT_ORDER_STATUS = 13;
	public static int MASTER_MODULE_TYPE = 14;
	public static int MASTER_CARRIER_TYPE = 15;
	public static int MASTER_INCOTERM = 16;
	public static int MASTER_REGION = 17;
	public static int MASTER_SHIPMENT_TYPE = 19;
	public static int MASTER_UNIT_TYPE = 21;	
	public static int MASTER_RATE_CLASS = 22;
	public static int MASTER_BL_SHIPPING_TYPE = 24;
	
	public static int MASTER_CHARGE_TYPE_AIR = 25; 				//Cargos para los carrier rates de la pestana air.
	public static int MASTER_CHARGE_TYPE_OCEAN_LCL = 26;		//Cargos para los carrier rates de la pestana ocean LCL.
	public static int MASTER_CHARGE_TYPE_OCEAN_20_40 = 27;		//Cargos para los carrier rates de la pestana ocean 20 y 40.
	public static int MASTER_CHARGE_TYPE_OTHER = 28;			//Cargos para los carrier rates en los other charges
	public static int MASTER_SAID_TO_CONTAIN = 29;
	public static int MASTER_CONTAINER_TYPE = 30;
	public static int MASTER_PACKING_GROUP_UN_CODES = 31;
	public static int MASTER_CLASS_UN_CODES = 32;
	public static int MASTER_BL_TYPE_OF_MOVES = 33;
	
	/**
	 * Constantes para los master values.
	 */
	public static int MASTER_VALUE_DEPARTMENT_EVERYONE = 1;
	public static int MASTER_VALUE_DEPARTMENT_RM = 2;
	public static int MASTER_VALUE_DEPARTMENT_IP = 3;
	public static int MASTER_VALUE_DEPARTMENT_FF = 4;
	
	public static int MASTER_VALUE_PHONE_TYPE_TEL = 14;
	public static int MASTER_VALUE_PHONE_TYPE_FAX = 16;
	
	public static int MASTER_VALUE_SHIPPING_TYPE_AIR = 393;
	public static int MASTER_VALUE_SHIPPING_TYPE_OCEAN = 394;
	public static int MASTER_VALUE_SHIPPING_TYPE_OCEAN_AND_AIR = 395;
	
	public static int MASTER_VALUE_CLIENT_ORDER_MODULE = 410;
	
	public static int MASTER_VALUE_REGION_USA = 399;
	public static int MASTER_VALUE_REGION_GER = 400;
	public static int MASTER_VALUE_REGION_COL = 401;
	
	public static int MASTER_VALUE_STATUS_UNCONFIRMED = 384;
	public static int MASTER_VALUE_STATUS_SCHEDULED = 385;
	public static int MASTER_VALUE_STATUS_IN_TRANSIT = 386;
	
	public static int MASTER_VALUE_TYPE_COPY = 411;
	public static int MASTER_VALUE_TYPE_ORIGINAL = 412;
	
	
	
	public static int MASTER_VALUE_LOCAL_DELIVERY_LCL = 415;
	
	public static int MASTER_VALUE_RATE_TYPE_AIR = 416;
	public static int MASTER_VALUE_RATE_TYPE_LCL = 417;
	public static int MASTER_VALUE_RATE_TYPE_20 = 418;
	public static int MASTER_VALUE_RATE_TYPE_40 = 419;
	
	public static int MASTER_VALUE_SHIPMENT_TYPE_MASTER = 420;
	public static int MASTER_VALUE_SHIPMENT_TYPE_HOUSE = 421;
	public static int MASTER_VALUE_SHIPMENT_TYPE_REGULAR = 422;
	
	
	
	public static int MASTER_VALUE_PREPAID = 423;
	public static int MASTER_VALUE_COLLECT = 424;
	
	public static int MASTER_VALUE_RATE_CLASS_GOODS= 449;
	public static int MASTER_VALUE_RATE_CLASS_DANGEROUS = 448;
	public static int MASTER_VALUE_RATE_CLASS_OVERSIZE = 450;
	public static int MASTER_VALUE_RATE_CLASS_REFRIGERATED = 451;
	
	public static int MASTER_VALUE_CHARGE_TYPE_OTHER =  465;
	
	public static int MASTER_VALUE_BL_SHIPPING_TYPE_LCL = 485;
	public static int MASTER_VALUE_BL_SHIPPING_TYPE_FCL = 486;
	public static int MASTER_VALUE_CHARGE_TYPE_AIR_FREIGHT = 492;
	public static int MASTER_VALUE_CHARGE_TYPE_OVERSIZE = 495;
	public static int MASTER_VALUE_CHARGE_TYPE_REFRIGERATED = 496;
	public static int MASTER_VALUE_CHARGE_TYPE_DANGEROUS = 497;
	public static int MASTER_VALUE_CHARGE_TYPE_UN_CHARGE = 498;
	public static int MASTER_VALUE_CHARGE_TYPE_LOCAL_DELIVERY = 513;
	
	public static int MASTER_VALUE_CONTAINER_20 = 514;
	public static int MASTER_VALUE_CONTAINER_20_FR = 515;
	public static int MASTER_VALUE_CONTAINER_40 = 516;
	public static int MASTER_VALUE_CONTAINER_40_FR = 517;
	public static int MASTER_VALUE_CONTAINER_40_HC = 518;
	public static int MASTER_VALUE_CONTAINER_45 = 519;
	public static int MASTER_VALUE_CONTAINER_53 = 520;
	
	public static int MASTER_VALUE_INLAND_FREIGHT_AWB = 534;
	public static int MASTER_VALUE_INLAND_FREIGHT_FCL = 540;
	public static int MASTER_VALUE_INLAND_FREIGHT_LCL = 541;
	
	
	public static int CARRIER_LOT_LOGISTICS_ID = 14;
	
	public static String SAID_TO_CONTAIN_AWB_MASTER = "CONSOLIDATED CARGO AS PER ATTACHED CARGO MANIFEST";
	/**
	 *CONSTANTES PARA CONVERSION DE UNIDADES
	 **/
	
	public static double KILOGRAM_TO_POUNDS = 2.20462262;
	public static double FOOT_TO_METERS = 0.3048;
	public static double FOOT_TO_INCHES = 12;
	public static double VOL_INCHES_CUBIC_TO_VOL_FEET_CUBIC = 0.000578703704;    // Factor de conversion de volumen en pulgadas cubicas a volumen en pies cubicos.
	
	
}
