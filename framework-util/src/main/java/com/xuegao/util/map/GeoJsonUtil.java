// package com.xuegao.util.map;
//
// import com.alibaba.fastjson.JSONArray;
// import com.alibaba.fastjson.JSONObject;
// import com.google.common.collect.Maps;
// import org.apache.commons.collections4.CollectionUtils;
//
// import java.util.*;
//
// public class GeoJsonUtil {
//
//     public static final String properties = "properties";
//     public static final String type = "type";
//     public static final String features = "features";
//     public static final String Feature = "Feature";
//     public static final String FeatureCollection = "FeatureCollection";
//     public static final String id = "id";
//     public static final String layer = "layer";
//     public static final String geometry = "geometry";
//     public static final String coordinates = "coordinates";
//     public static final String name = "name";
//     public static final String remark = "remark";
//     public static final String width = "width";
//     public static final String color = "color";
//     public static final String materialType = "materialType";
//     public static final String style = "style";
//     // 线路
//     public static final String LineString = "LineString";
//     public static final String polyline = "polyline";
//
//     // 点
//     public static final String billboard = "billboard";
//     public static final String point = "point";
//     public static final String fontBillboard = "fontBillboard";
//
//
//     /**
//      * <br/> @Title: 从单个 featureJson 中 获取到 PropertiesType
//      * <br/> @Description:
//      * <br/> @MethodName: getPropertiesTypeByFeature
//      * <br/> @param featureJsonObject:
//      * <br/> @return: java.lang.String
//      * <br/> @author: fjm
//      * <br/> @date: 2024/4/19 18:24
//      */
//     public static String getPropertiesTypeByFeature(JSONObject featureJsonObject) {
//         if (Objects.isNull(featureJsonObject)) {
//             return null;
//         }
//         if (!featureJsonObject.containsKey(properties)) {
//             return null;
//         }
//         JSONObject propertiesJsonObj = featureJsonObject.getJSONObject(properties);
//         if (!propertiesJsonObj.containsKey(type)) {
//             return null;
//         }
//         return propertiesJsonObj.getString(type);
//     }
//
//     /**
//      * <br/> @Title: 将 多个大的 geoJson 转为 多个 futureJson
//      * <br/> @Description:
//      * <br/> @MethodName: convertMap
//      * <br/> @param geoJsonObjectList:
//      * <br/> @return: java.util.Map<java.lang.String,java.util.List<com.alibaba.fastjson.JSONObject>>
//      * <br/> @author: fjm
//      * <br/> @date: 2024/4/19 18:23
//      */
//     public static Map<String, List<JSONObject>> geoJsonToFetureJsonMap(List<JSONObject> geoJsonObjectList) {
//         if (CollectionUtils.isEmpty(geoJsonObjectList)) {
//             return Maps.newHashMap();
//         }
//         Map<String, List<JSONObject>> resultMap = new HashMap<>(10);
//         for (JSONObject geoJson : geoJsonObjectList) {
//             Map<String, List<JSONObject>> converted = geoJsonToFetureJsonMap(geoJson);
//             for (Map.Entry<String, List<JSONObject>> entry : converted.entrySet()) {
//                 String type = entry.getKey();
//                 List<JSONObject> valueList = entry.getValue();
//                 resultMap.computeIfAbsent(type, k -> new ArrayList<>(10)).addAll(valueList);
//             }
//         }
//         return resultMap;
//     }
//
//     /**
//      * <br/> @Title: 将 单个大的 geoJson 转为 多个 futureJson
//      * <br/> @Description:
//      * <br/> @MethodName: convertMap
//      * <br/> @param geoJsonObject:
//      * <br/> @return: key = PropertiesType， value = 该类型下的所有 futureJson
//      * <br/> @author: fjm
//      * <br/> @date: 2024/4/19 18:24
//      */
//     public static Map<String, List<JSONObject>> geoJsonToFetureJsonMap(JSONObject geoJsonObject) {
//         if (Objects.isNull(geoJsonObject)) {
//             return Maps.newHashMap();
//         }
//         if (!geoJsonObject.containsKey(features)) {
//             return Maps.newHashMap();
//         }
//         Map<String, List<JSONObject>> resultMap = new HashMap<>(10);
//
//         JSONArray featuresJsonArr = geoJsonObject.getJSONArray(features);
//         for (Object o : featuresJsonArr) {
//             JSONObject temp = (JSONObject) o;
//             if (Objects.isNull(temp)) {
//                 continue;
//             }
//             String type = getPropertiesTypeByFeature(temp);
//             resultMap.computeIfAbsent(type, k -> new ArrayList<>(10)).add(temp);
//         }
//         return resultMap;
//     }
//
//     /**
//      * <br/> @Title: 将 多个 featureList 转为 一个大的 geoJson
//      * <br/> @Description:
//      * <br/> @MethodName: featureListToGeoJson
//      * <br/> @param featureList:
//      * <br/> @return: com.alibaba.fastjson.JSONObject
//      * <br/> @author: fjm
//      * <br/> @date: 2024/4/19 18:25
//      */
//     public static JSONObject featureListToGeoJson(List<JSONObject> featureList) {
//         if (CollectionUtils.isEmpty(featureList)) {
//             return new JSONObject();
//         }
//         JSONObject result = new JSONObject();
//         result.put(type, FeatureCollection);
//         JSONObject layerJson = new JSONObject();
//         layerJson.put(id, UUID.randomUUID());
//         result.put(layer, layerJson);
//         result.put(features, featureList);
//         return result;
//     }
//
//     /**
//      * <br/> @Title: 构建线路模型
//      * <br/> @Description:
//      * <br/> @MethodName: featureListToGeoJson
//      * <br/> @param featureList: 多个点的数据
//      * <br/> @return: com.alibaba.fastjson.JSONObject
//      * <br/> @author: fjm
//      * <br/> @date: 2024/4/19 18:25
//      */
//     public static JSONObject buildPolyline(String routeName, List<JSONObject> featureList) {
//         JSONObject jsonObject = new JSONObject();
//         jsonObject.put(type, Feature);
//         JSONObject propertiesJsonObject = new JSONObject();
//         propertiesJsonObject.put(name, routeName);
//         propertiesJsonObject.put(remark, routeName);
//         propertiesJsonObject.put(id, UUID.randomUUID().toString());
//         propertiesJsonObject.put(type, polyline);
//         JSONObject styleJsonObject = new JSONObject();
//         styleJsonObject.put(width, "10");
//         styleJsonObject.put(color, "#ffff00");
//         // styleJsonObject.put("clampToGround", "true");
//         styleJsonObject.put(materialType, "LineFlow");
//         JSONObject materialOptionsJsonObject = new JSONObject();
//         materialOptionsJsonObject.put("color", "rgb(0,255,255)");
//         materialOptionsJsonObject.put("image", "/img/textures/arrow-h.png");
//         materialOptionsJsonObject.put("repeat_x", "20");
//         materialOptionsJsonObject.put("repeat_y", "1");
//         materialOptionsJsonObject.put("speed", "30");
//         materialOptionsJsonObject.put("materialType", "LineFlow");
//         styleJsonObject.put("materialOptions", materialOptionsJsonObject);
//         JSONObject labelJsonObject = new JSONObject();
//         labelJsonObject.put("color", "#ffffff");
//         labelJsonObject.put("text", routeName);
//         styleJsonObject.put("label", labelJsonObject);
//
//         propertiesJsonObject.put(style, styleJsonObject);
//         jsonObject.put(properties, propertiesJsonObject);
//
//         JSONObject geometryJsonObject = new JSONObject();
//         geometryJsonObject.put(type, LineString);
//
//         if (CollectionUtils.isEmpty(featureList)) {
//             return new JSONObject();
//         }
//         JSONArray finalCoordinatesJsonArr = new JSONArray();
//         for (JSONObject temp : featureList) {
//             if (!temp.containsKey(geometry)) {
//                 continue;
//             }
//             JSONObject tempGeometryJsonObject = temp.getJSONObject(geometry);
//             JSONArray tempCoordinatesJsonArr = tempGeometryJsonObject.getJSONArray(coordinates);
//             if (!temp.containsKey(properties)) {
//                 continue;
//             }
//             JSONObject propertiesJson = temp.getJSONObject(properties);
//             String typeStr = propertiesJson.getString(type);
//             if (billboard.equals(typeStr) || point.equals(typeStr) || fontBillboard.equalsIgnoreCase(typeStr)) {
//                 JSONArray coordinatesJsonArr = new JSONArray();
//                 coordinatesJsonArr.addAll(tempCoordinatesJsonArr);
//                 finalCoordinatesJsonArr.add(coordinatesJsonArr);
//             }
//             geometryJsonObject.put(coordinates, finalCoordinatesJsonArr);
//         }
//         jsonObject.put(geometry, geometryJsonObject);
//         return jsonObject;
//     }
// }
