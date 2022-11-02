// package com.xuegao.core.model;
//
// import com.alibaba.ttl.TransmittableThreadLocal;
//
// public class ContextUtil {
//     private static ThreadLocal<Context> CURRENT_LOCAL_CONTEXT = new TransmittableThreadLocal<>();
//
//
//     public static Context get() {
//         return CURRENT_LOCAL_CONTEXT.get();
//     }
//
//     public static void set(Context context) {
//         CURRENT_LOCAL_CONTEXT.set(context);
//     }
//
//     public static void remove() {
//         CURRENT_LOCAL_CONTEXT.remove();
//     }
//
//     public static void addGlobalParam(String key, Object value) {
//         Context context = get();
//         if (context == null) {
//             set(new Context());
//             context = get();
//         }
//
//         context.addGlobalParam(key, value);
//     }
//
//     public Object getGlobalParam(String key) {
//         Object result = null;
//
//         Context context = get();
//         if (context != null) {
//             result = context.getGLOBAL_PARAM_MAP(key);
//         }
//
//         return result;
//     }
//
// }
