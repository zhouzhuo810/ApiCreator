# ApiCreator
A Android develop tools for API Creatirng.

## 环境要求(下面之一)
- InteliJ IDEA
- Android Studio

## 功能介绍

方式1: 将小幺鸡接口文档转成Android实体类和RxJava2+Retrofit2+Okhttp3 Api。

方式2：将小幺鸡替换成我的开源项目[ZzApiDoc](https://github.com/zhouzhuo810/ZzApiDoc)和[ZzApiDoc-Android](https://github.com/zhouzhuo810/ZzApiDoc-Android)

## 用法介绍

- 登陆http://www.xiaoyaoji.cn/
- 编写接口文档
- 选中某个项目文档，导出JSON格式
- 右键Android Studio某个包名，选择Api Creator。
- 复制JSON文件内容到对话框，点击OK。
- 刷新改包名。

可以看到会有几个Api文件和bean文件夹，bean里面是java实体类。

## 图片示例

![demo](https://github.com/zhouzhuo810/ApiCreator/blob/master/apicreator.gif)


## License

```
Copyright © zhouzhuo810

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
