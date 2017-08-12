package me.zhouzhuo810.apicreator.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.intellij.openapi.project.Project;
import me.zhouzhuo810.apicreator.bean.ApiEntity;
import me.zhouzhuo810.apicreator.bean.ArgEntity;
import me.zhouzhuo810.apicreator.utils.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class JsonDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane tvJson;

    private String path;
    private String packageName;
    private Project project;

    public JsonDialog(String path, String packageName, Project project) {

        this.path = path;
        this.packageName = packageName;
        this.project = project;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        String json = tvJson.getText().trim();

        try {
            Gson gson = new GsonBuilder().create();
            ApiEntity api = gson.fromJson(json, ApiEntity.class);

            if (api != null) {

                List<ApiEntity.ModulesBean> modules = api.getModules();
                if (modules != null) {
                    for (int i = 0; i < modules.size(); i++) {
                        ApiEntity.ModulesBean modulesBean = modules.get(i);
                        //全局参数
                        String requestArgs = modulesBean.getRequestArgs();
                        if (requestArgs != null) {
                            ArgEntity argEntity = gson.fromJson("{\"data\":" + requestArgs + "}", ArgEntity.class);
                            if (argEntity != null && argEntity.getData() != null) {
                                //有全局参数
                                List<ArgEntity.DataBean> data1 = argEntity.getData();
                                for (int i4 = 0; i4 < data1.size(); i4++) {
                                    ArgEntity.DataBean dataBean1 = data1.get(i4);
                                    String name1 = dataBean1.getName();
                                    String type1 = dataBean1.getType();

                                    //接口分组
                                    List<ApiEntity.ModulesBean.FoldersBean> folders = modulesBean.getFolders();
                                    if (folders != null) {
                                        //Api单例类
                                        StringBuilder sbApi = new StringBuilder();
                                        sbApi.append("package ").append(packageName).append(";");
                                        sbApi.append("\n");
                                        sbApi.append("\nimport java.io.File;");
                                        sbApi.append("\nimport java.net.CookieHandler;");
                                        sbApi.append("\nimport java.net.CookieManager;");
                                        sbApi.append("\nimport java.net.CookiePolicy;");
                                        sbApi.append("\nimport java.util.concurrent.TimeUnit;");
                                        sbApi.append("\n\n");
                                        sbApi.append("\nimport okhttp3.Cache;");
                                        sbApi.append("\nimport okhttp3.OkHttpClient;");
                                        sbApi.append("\nimport okhttp3.logging.HttpLoggingInterceptor;");
                                        sbApi.append("\n\n");
                                        sbApi.append("\nimport retrofit2.Retrofit;");
                                        sbApi.append("\nimport retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;");
                                        sbApi.append("\nimport retrofit2.converter.gson.GsonConverterFactory;");
                                        sbApi.append("\n");
                                        sbApi.append("\nimport android.content.Context;");
                                        sbApi.append("\n");
                                        sbApi.append("\n/**");
                                        sbApi.append("\n * ").append("Api调用");
                                        sbApi.append("\n */");
                                        sbApi.append("\npublic class Api").append(" {");

                                        for (int i1 = 0; i1 < folders.size(); i1++) {
                                            ApiEntity.ModulesBean.FoldersBean foldersBean = folders.get(i1);
                                            String ip = foldersBean.getIp();
                                            StringBuilder sb = new StringBuilder();
                                            //包名导入
                                            sb.append("package ").append(packageName).append(";");
                                            sb.append("\n");
                                            sb.append("\nimport retrofit2.http.*;");
                                            sb.append("\nimport rx.Observable;");
                                            sb.append("\nimport ").append(packageName).append(".entity.*;");
                                            sb.append("\n\n");
                                            sb.append("\n/**");
                                            sb.append("\n * ").append(foldersBean.getName());
                                            sb.append("\n */");
                                            sb.append("\npublic interface Api").append(i1).append(" {");
                                            sbApi.append("\n    private static final String SERVER_IP").append(i1).append(" = ").append("\"").append(ip == null ? "":ip).append("\"").append(";");
                                            sbApi.append("\n    private static Api").append(i1).append(" api").append(i1).append(";");
                                            sbApi.append("\n    public static Api").append(i1).append(" getApi").append(i1).append("(Context context) {");
                                            sbApi.append("\n        if (api").append(i1).append(" == null) {");
                                            sbApi.append("\n            synchronized (Api.class) {");
                                            sbApi.append("\n                if (api").append(i1).append(" == null) {");
                                            sbApi.append("\n                    File cache = context.getCacheDir();");
                                            sbApi.append("\n                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();");
                                            sbApi.append("\n                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);");
                                            sbApi.append("\n                    HttpLoggingInterceptor logging1 = new HttpLoggingInterceptor();");
                                            sbApi.append("\n                    logging1.setLevel(HttpLoggingInterceptor.Level.BODY);");
                                            sbApi.append("\n                    OkHttpClient client = new OkHttpClient.Builder()");
                                            sbApi.append("\n                            .cache(new Cache(cache, 10 * 1024 * 1024))");
                                            sbApi.append("\n                            .readTimeout(20, TimeUnit.SECONDS)");
                                            sbApi.append("\n                            .writeTimeout(20, TimeUnit.SECONDS)");
                                            sbApi.append("\n                            .connectTimeout(20, TimeUnit.SECONDS)");
                                            sbApi.append("\n                            .addInterceptor(logging)");
                                            sbApi.append("\n                            .addInterceptor(logging1)");
                                            sbApi.append("\n                            .build();");
                                            sbApi.append("\n\n");
                                            sbApi.append("\n                    CookieHandler.setDefault(getCookieManager());");
                                            sbApi.append("\n                    Retrofit retrofit = new Retrofit.Builder()");
                                            sbApi.append("\n                            .client(client)");
                                            sbApi.append("\n                            .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器");
                                            sbApi.append("\n                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器");
                                            sbApi.append("\n                            .baseUrl(SERVER_IP").append(i1).append(")");
                                            sbApi.append("\n                            .build();");
                                            sbApi.append("\n                    api").append(i1).append(" = retrofit.create(Api").append(i1).append(".class);");
                                            sbApi.append("\n                }");
                                            sbApi.append("\n            }");
                                            sbApi.append("\n        }");
                                            sbApi.append("\n        return api").append(i1).append(";");
                                            sbApi.append("\n    }");
                                            sbApi.append("\n\n");




                                            //接口
                                            List<ApiEntity.ModulesBean.FoldersBean.ChildrenBean> children = foldersBean.getChildren();
                                            if (children != null) {
                                                for (ApiEntity.ModulesBean.FoldersBean.ChildrenBean childrenBean : children) {

                                                    //实体类内容
                                                    StringBuilder sbEntity = new StringBuilder();
                                                    sbEntity.append("package ").append(packageName).append(".entity;");
                                                    sbEntity.append("\n");
                                                    sbEntity.append("\nimport java.util.List;");
                                                    sbEntity.append("\n/**");
                                                    sbEntity.append("\n * ").append(childrenBean.getName());
                                                    sbEntity.append("\n */");

                                                    //接口地址
                                                    String url = childrenBean.getUrl();
                                                    System.out.println(url);

                                                    //方法描述
                                                    String desc = childrenBean.getDescription();
                                                    sb.append("\n   /*");
                                                    sb.append("\n    * ").append(desc);
                                                    sb.append("\n    */");

                                                    //方法名
                                                    String m = url.substring(url.lastIndexOf("/") + 1, url.length());

                                                    //请求方式GET或POST
                                                    String method = childrenBean.getRequestMethod();
                                                    if (method.equals("GET")) {
                                                        sb.append("\n   @GET").append("(\"").append(url).append("\")");
                                                    } else {
                                                        sb.append("\n   @FormUrlEncoded");
                                                        sb.append("\n   @POST").append("(\"").append(url).append("\")");
                                                    }
//                                        System.out.println(method);

                                                    String beanClazz = m.substring(0, 1).toUpperCase() + m.substring(1, m.length()) + "Result";


                                                    //接口返回数据
                                                    String responseData = childrenBean.getResponseArgs();
                                                    try {
                                                        sbEntity.append("\npublic class ").append(beanClazz).append(" {");
                                                        JSONArray root = new JSONArray(responseData);
                                                        generateJavaBean2(root, sbEntity);
                                                        sbEntity.append("\n}");
                                                        System.out.println(sbEntity.toString());
                                                        FileUtil.writeFile(path + File.separator + "entity", beanClazz + ".java", sbEntity.toString());
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        System.out.println(url + "接口的返回json实例解析异常");
                                                    }

                                                    //TODO 创建实体类
                                                    //接口请求参数
                                                    String requestArgs1 = childrenBean.getRequestArgs();
                                                    ArgEntity argEntity1 = gson.fromJson("{\"data\":" + requestArgs1 + "}", ArgEntity.class);
                                                    if (argEntity1 != null && argEntity1.getData() != null) {

                                                        sb.append("\n   Observable<").append(beanClazz).append("> ").append(m).append("(");
                                                        //有参数
                                                        List<ArgEntity.DataBean> data = argEntity1.getData();
                                                        //添加全局参数
                                                        if (method.equals("GET")) {
                                                            if (type1.equals("number")) {
                                                                sb.append("@Query(\"").append(name1).append("\") ").append("int ").append(name1).append(",");
                                                            } else {
                                                                sb.append("@Query(\"").append(name1).append("\") ").append("String ").append(name1).append(",");
                                                            }
                                                        } else {
                                                            if (type1.equals("number")) {
                                                                sb.append("@Field(\"").append(name1).append("\") ").append("int ").append(name1).append(",");
                                                            } else {
                                                                sb.append("@Field(\"").append(name1).append("\") ").append("String ").append(name1).append(",");
                                                            }
                                                        }
                                                        for (ArgEntity.DataBean aData : data) {
                                                            String name = aData.getName();
                                                            String type = aData.getType();
                                                            if (method.equals("GET")) {
                                                                if (type.equals("number")) {
                                                                    sb.append("@Query(\"").append(name).append("\") ").append("int ").append(name).append(",");
                                                                } else {
                                                                    sb.append("@Query(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                                }
                                                            } else {
                                                                if (type.equals("number")) {
                                                                    sb.append("@Field(\"").append(name).append("\") ").append("int ").append(name).append(",");
                                                                } else {
                                                                    sb.append("@Field(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                                }
                                                            }
                                                        }
                                                        sb.deleteCharAt(sb.length() - 1);

                                                    } else {
                                                        sb.append("\n   Observable<Object> ").append(m).append("(");
                                                        //无参数
                                                        assert argEntity1 != null;
                                                        List<ArgEntity.DataBean> data = argEntity1.getData();
                                                        boolean has = false;
                                                        for (ArgEntity.DataBean aData : data) {
                                                            has = true;
                                                            String name = aData.getName();
                                                            String type = aData.getType();
                                                            if (method.equals("GET")) {
                                                                sb.append("@Query(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                            } else {
                                                                sb.append("@Field(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                            }
                                                        }
                                                        if (has) {
                                                            sb.deleteCharAt(sb.length() - 1);
                                                        }
                                                    }
                                                    sb.append(");   ");
                                                }
                                            }
                                            sb.append("\n}");
                                            //TODO 创建Api
                                            FileUtil.writeFile(path, "Api" + i1 + ".java", sb.toString());
                                        }
                                        sbApi.append("\n    private static CookieManager getCookieManager() {");
                                        sbApi.append("\n        CookieManager cookieManager = new CookieManager();");
                                        sbApi.append("\n        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);");
                                        sbApi.append("\n        return cookieManager;");
                                        sbApi.append("\n    }");
                                        sbApi.append("\n}");
                                        //TODO 创建Api调用
                                        FileUtil.writeFile(path, "Api.java", sbApi.toString());
                                    }
                                }
                            } else {
                                //无全局参数

                            }
                        } else {
                            //无全局参数

                            //接口分组
                            List<ApiEntity.ModulesBean.FoldersBean> folders = modulesBean.getFolders();
                            if (folders != null) {
                                //Api单例类
                                StringBuilder sbApi = new StringBuilder();
                                sbApi.append("package ").append(packageName).append(";");
                                sbApi.append("\n");
                                sbApi.append("\nimport java.io.File;");
                                sbApi.append("\nimport java.net.CookieHandler;");
                                sbApi.append("\nimport java.net.CookieManager;");
                                sbApi.append("\nimport java.net.CookiePolicy;");
                                sbApi.append("\nimport java.util.concurrent.TimeUnit;");
                                sbApi.append("\n\n");
                                sbApi.append("\nimport okhttp3.Cache;");
                                sbApi.append("\nimport okhttp3.OkHttpClient;");
                                sbApi.append("\nimport okhttp3.logging.HttpLoggingInterceptor;");
                                sbApi.append("\n\n");
                                sbApi.append("\nimport retrofit2.Retrofit;");
                                sbApi.append("\nimport retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;");
                                sbApi.append("\nimport retrofit2.converter.gson.GsonConverterFactory;");
                                sbApi.append("\n");
                                sbApi.append("\nimport android.content.Context;");
                                sbApi.append("\n");
                                sbApi.append("\n/**");
                                sbApi.append("\n * ").append("Api调用");
                                sbApi.append("\n */");
                                sbApi.append("\npublic class Api").append(" {");

                                for (int i1 = 0; i1 < folders.size(); i1++) {
                                    ApiEntity.ModulesBean.FoldersBean foldersBean = folders.get(i1);
                                    StringBuilder sb = new StringBuilder();
                                    //包名导入
                                    sb.append("package ").append(packageName).append(";");
                                    sb.append("\n");
                                    sb.append("\nimport retrofit2.http.*;");
                                    sb.append("\nimport rx.Observable;");
                                    sb.append("\nimport ").append(packageName).append(".entity.*;");
                                    sb.append("\n\n");
                                    sb.append("\n/**");
                                    sb.append("\n * ").append(foldersBean.getName());
                                    sb.append("\n */");
                                    sb.append("\npublic interface Api").append(i1).append(" {");
                                    sbApi.append("\n    private static final String SERVER_IP").append(i1).append(" = ").append("\"\";");
                                    sbApi.append("\n    private static Api").append(i1).append(" api").append(i1).append(";");
                                    sbApi.append("\n    public static Api").append(i1).append(" getApi").append(i1).append("(Context context) {");
                                    sbApi.append("\n        if (api").append(i1).append(" == null) {");
                                    sbApi.append("\n            synchronized (Api.class) {");
                                    sbApi.append("\n                if (api").append(i1).append(" == null) {");
                                    sbApi.append("\n                    File cache = context.getCacheDir();");
                                    sbApi.append("\n                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();");
                                    sbApi.append("\n                    logging.setLevel(HttpLoggingInterceptor.Level.BASIC);");
                                    sbApi.append("\n                    HttpLoggingInterceptor logging1 = new HttpLoggingInterceptor();");
                                    sbApi.append("\n                    logging1.setLevel(HttpLoggingInterceptor.Level.BODY);");
                                    sbApi.append("\n                    OkHttpClient client = new OkHttpClient.Builder()");
                                    sbApi.append("\n                            .cache(new Cache(cache, 10 * 1024 * 1024))");
                                    sbApi.append("\n                            .readTimeout(20, TimeUnit.SECONDS)");
                                    sbApi.append("\n                            .writeTimeout(20, TimeUnit.SECONDS)");
                                    sbApi.append("\n                            .connectTimeout(20, TimeUnit.SECONDS)");
                                    sbApi.append("\n                            .addInterceptor(logging)");
                                    sbApi.append("\n                            .addInterceptor(logging1)");
                                    sbApi.append("\n                            .build();");
                                    sbApi.append("\n\n");
                                    sbApi.append("\n                    CookieHandler.setDefault(getCookieManager());");
                                    sbApi.append("\n                    Retrofit retrofit = new Retrofit.Builder()");
                                    sbApi.append("\n                            .client(client)");
                                    sbApi.append("\n                            .addConverterFactory(GsonConverterFactory.create())//添加 json 转换器");
                                    sbApi.append("\n                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加 RxJava 适配器");
                                    sbApi.append("\n                            .baseUrl(SERVER_IP").append(i1).append(")");
                                    sbApi.append("\n                            .build();");
                                    sbApi.append("\n                    api").append(i1).append(" = retrofit.create(Api").append(i1).append(".class);");
                                    sbApi.append("\n                }");
                                    sbApi.append("\n            }");
                                    sbApi.append("\n        }");
                                    sbApi.append("\n        return api").append(i1).append(";");
                                    sbApi.append("\n    }");
                                    sbApi.append("\n\n");

                                    //接口
                                    List<ApiEntity.ModulesBean.FoldersBean.ChildrenBean> children = foldersBean.getChildren();
                                    if (children != null) {
                                        for (ApiEntity.ModulesBean.FoldersBean.ChildrenBean childrenBean : children) {

                                            //实体类内容
                                            StringBuilder sbEntity = new StringBuilder();
                                            sbEntity.append("package ").append(packageName).append(".entity;");
                                            sbEntity.append("\n");
                                            sbEntity.append("\nimport java.util.List;");
                                            sbEntity.append("\n/**");
                                            sbEntity.append("\n * ").append(childrenBean.getName());
                                            sbEntity.append("\n */");

                                            //接口地址
                                            String url = childrenBean.getUrl();
                                            System.out.println(url);

                                            //方法描述
                                            String desc = childrenBean.getDescription();
                                            sb.append("\n   /*");
                                            sb.append("\n    * ").append(desc);
                                            sb.append("\n    */");

                                            //方法名
                                            String m = url.substring(url.lastIndexOf("/") + 1, url.length());
                                            //请求方式GET或POST
                                            String method = childrenBean.getRequestMethod();
                                            if (method.equals("GET")) {
                                                sb.append("\n   @GET").append("(\"").append(url).append("\")");
                                            } else {
                                                sb.append("\n   @FormUrlEncoded");
                                                sb.append("\n   @POST").append("(\"").append(url).append("\")");
                                            }
//                                        System.out.println(method);

                                            String beanClazz = m.substring(0, 1).toUpperCase() + m.substring(1, m.length()) + "Result";


                                            //接口返回数据
                                            String responseData = childrenBean.getResponseArgs();
                                            try {
                                                sbEntity.append("\npublic class ").append(beanClazz).append(" {");
                                                JSONArray root = new JSONArray(responseData);
                                                generateJavaBean2(root, sbEntity);
                                                sbEntity.append("\n}");
                                                System.out.println(sbEntity.toString());
                                                FileUtil.writeFile(path + File.separator + "entity", beanClazz + ".java", sbEntity.toString());
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                System.out.println(url + "接口的返回json实例解析异常");
                                            }

                                            //TODO 创建实体类
                                            //接口请求参数
                                            String requestArgs1 = childrenBean.getRequestArgs();
                                            ArgEntity argEntity1 = gson.fromJson("{\"data\":" + requestArgs1 + "}", ArgEntity.class);
                                            if (argEntity1 != null && argEntity1.getData() != null) {

                                                sb.append("\n   Observable<").append(beanClazz).append("> ").append(m).append("(");
                                                //有参数
                                                List<ArgEntity.DataBean> data = argEntity1.getData();
                                                boolean has = false;
                                                for (ArgEntity.DataBean aData : data) {
                                                    has = true;
                                                    String name = aData.getName();
                                                    String type = aData.getType();
                                                    if (method.equals("GET")) {
                                                        if (type.equals("number")) {
                                                            sb.append("@Query(\"").append(name).append("\") ").append("int ").append(name).append(",");
                                                        } else {
                                                            sb.append("@Query(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                        }
                                                    } else {
                                                        if (type.equals("number")) {
                                                            sb.append("@Field(\"").append(name).append("\") ").append("int ").append(name).append(",");
                                                        } else {
                                                            sb.append("@Field(\"").append(name).append("\") ").append("String ").append(name).append(",");
                                                        }
                                                    }
                                                }
                                                if (has)
                                                    sb.deleteCharAt(sb.length() - 1);
                                            } else {
                                                //无参数
                                                sb.append("\n   Observable<").append(beanClazz).append("> ").append(m).append("(");
                                            }
                                            sb.append(");   ");
                                        }
                                    }
                                    sb.append("\n}");
                                    //TODO 创建Api
                                    FileUtil.writeFile(path, "Api" + i1 + ".java", sb.toString());
                                }
                                sbApi.append("\n    private static CookieManager getCookieManager() {");
                                sbApi.append("\n        CookieManager cookieManager = new CookieManager();");
                                sbApi.append("\n        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);");
                                sbApi.append("\n        return cookieManager;");
                                sbApi.append("\n    }");
                                sbApi.append("\n}");
                                //TODO 创建Api调用
                                FileUtil.writeFile(path, "Api.java", sbApi.toString());
                            }

                        }
                    }
                }

            } else {
                System.out.println("格式解析异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispose();
    }

    /**
     * 带注释
     */
    private void generateJavaBean2(JSONArray jsonArray, StringBuilder sb) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String type = jsonObject.getString("type");
            String desc = null;
            try {
                desc = jsonObject.getString("description");
            } catch (Exception e) {

            }
            System.out.println("type="+type+", name="+name);
            switch (type) {
                case "number":
                    sb.append("\n       private int ").append(name).append("; ").append(desc == null ? "" : " //" + desc);
                    //setter
                    sb.append("\n       public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("(int ").append(name).append(") {");
                    sb.append("\n           this.").append(name).append(" = ").append(name).append(";");
                    sb.append("\n       }");
                    //getter
                    sb.append("\n       public int get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("() {");
                    sb.append("\n           return ").append(name).append(";");
                    sb.append("\n       }");

                    break;
                case "string":
                    generateString(name, desc, sb);
                    break;
                case "object":
                    sb.append("\n       private ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append(" ").append(name).append(";");

                    //setter
                    sb.append("\n       public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("(")
                            .append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity ").append(name).append(") {");
                    sb.append("\n           this.").append(name).append(" = ").append(name).append(";");
                    sb.append("\n       }");
                    //getter
                    sb.append("\n       public ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity")
                            .append(" get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("() {");
                    sb.append("\n           return ").append(name).append(";");
                    sb.append("\n       }");

                    sb.append("\n       public static class ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append(" {");
                    JSONArray children = jsonObject.getJSONArray("children");
                    if (children != null && children.length() > 0) {
                        for (int j = 0; j < children.length(); j++) {
                            JSONArray child = children.getJSONObject(j).getJSONArray("children");
                            if (child != null && child.length() > 0) {
                                generateObj(children.getJSONObject(j).getString("name"), sb);
                                generateJavaBean2(child, sb);
                                sb.append("\n       }");
                            } else {
                                try {
                                    String d = children.getJSONObject(j).getString("description");
                                    generateString(children.getJSONObject(j).getString("name"), d, sb);
                                } catch (Exception e) {
                                    generateString(children.getJSONObject(j).getString("name"), null, sb);
                                }
                            }
                        }
                    }
                    sb.append("\n       }");
                    break;
                case "array[object]":

                    sb.append("\n       private List<").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append("> ").append(name).append(";");

                    //setter
                    sb.append("\n       public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("(List<")
                            .append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append("> ").append(name).append(") {");
                    sb.append("\n           this.").append(name).append(" = ").append(name).append(";");
                    sb.append("\n       }");
                    //getter
                    sb.append("\n       public List<").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity")
                            .append("> get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("() {");
                    sb.append("\n           return ").append(name).append(";");
                    sb.append("\n       }");

                    sb.append("\n       public static class ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append(" {");
                    JSONArray children2 = jsonObject.getJSONArray("children");
                    if (children2 != null && children2.length() > 0) {
                        generateJavaBean2(children2, sb);
                    }
                    sb.append("\n       }");

                    break;
            }

        }

    }

    private void generateString(String name ,String desc, StringBuilder sb) {
        sb.append("\n       private String ").append(name).append("; ").append(desc == null ? "" : " //" + desc);
        //setter
        sb.append("\n       public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("(String ").append(name).append(") {");
        sb.append("\n           this.").append(name).append(" = ").append(name).append(";");
        sb.append("\n       }");
        //getter
        sb.append("\n       public String get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("() {");
        sb.append("\n           return ").append(name).append(";");
        sb.append("\n       }");
    }

    private void generateObj(String name, StringBuilder sb) {
        sb.append("\n       private ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append(" ").append(name).append(";");

        //setter
        sb.append("\n       public void set").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("(")
                .append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity ").append(name).append(") {");
        sb.append("\n           this.").append(name).append(" = ").append(name).append(";");
        sb.append("\n       }");
        //getter
        sb.append("\n       public ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity")
                .append(" get").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("() {");
        sb.append("\n           return ").append(name).append(";");
        sb.append("\n       }");

        sb.append("\n       public static class ").append(name.substring(0, 1).toUpperCase()).append(name.substring(1, name.length())).append("Entity").append(" {");
    }


    private void generateJavaBean(JSONObject jsonObject, StringBuilder sb) {
        Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object child = jsonObject.get(key);
            if (child instanceof String) {
                sb.append("\n       private String ").append(key).append(";");
                //setter
                sb.append("\n       public void set").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("(String ").append(key).append(") {");
                sb.append("\n           this.").append(key).append(" = ").append(key).append(";");
                sb.append("\n       }");
                //getter
                sb.append("\n       public String get").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("() {");
                sb.append("\n           return ").append(key).append(";");
                sb.append("\n       }");

            } else if (child instanceof JSONObject) {
                sb.append("\n       private ").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity").append(" ").append(key).append(";");

                //setter
                sb.append("\n       public void set").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("(")
                        .append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity ").append(key).append(") {");
                sb.append("\n           this.").append(key).append(" = ").append(key).append(";");
                sb.append("\n       }");
                //getter
                sb.append("\n       public ").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity")
                        .append(" get").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("() {");
                sb.append("\n           return ").append(key).append(";");
                sb.append("\n       }");

                sb.append("\n       public static class ").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity").append(" {");
                generateJavaBean((JSONObject) child, sb);
                sb.append("\n       }");
            } else if (child instanceof JSONArray) {
                sb.append("\n       private List<").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity").append("> ").append(key).append(";");

                //setter
                sb.append("\n       public void set").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("(List<")
                        .append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity").append("> ").append(key).append(") {");
                sb.append("\n           this.").append(key).append(" = ").append(key).append(";");
                sb.append("\n       }");
                //getter
                sb.append("\n       public List<").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity")
                        .append("> get").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("() {");
                sb.append("\n           return ").append(key).append(";");
                sb.append("\n       }");

                JSONObject childObj = ((JSONArray) child).getJSONObject(0);
                sb.append("\n       public static class ").append(key.substring(0, 1).toUpperCase()).append(key.substring(1, key.length())).append("Entity").append(" {");
                generateJavaBean(childObj, sb);
                sb.append("\n       }");
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
