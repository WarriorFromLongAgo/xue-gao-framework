// package com.xuegao.util.collection;
//
// import com.google.common.collect.Lists;
// import org.apache.commons.collections4.CollectionUtils;
//
// import java.util.List;
// import java.util.Objects;
// import java.util.stream.Collectors;
//
// public class FmkCollectionUtil {
//
//     public static <T> List<T> ofNullable(List<T> inputList) {
//         if (CollectionUtils.isEmpty(inputList)) {
//             return Lists.newArrayList();
//         }
//         return inputList;
//     }
//
//     public static <T> List<T> filterNull(List<T> inputList) {
//         inputList = ofNullable(inputList);
//         return inputList.stream().filter(Objects::nonNull).collect(Collectors.toList());
//     }
// }
