package com.jingna.hulu.huluapp.model;

import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */

public class VideoListModel {

    /**
     * status : SUCCESS
     * errorCode : null
     * errorTitle : null
     * errorMsg : null
     * data : [{"id":108,"userName":"gm","password":"123","userPic":null,"peopleName":"后台测试员","isDelete":0,"joinTime":1542758400000,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1542787396000,"updateDate":1542937159000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"10","status":1,"num1":"15","num2":null,"num3":null,"num4":null,"num5":"","roleName":"超级无敌管理员","roleId":1,"departmentName":"鹤岗护路办/二级","group":null},{"id":107,"userName":"zz","password":"123","userPic":"/upload/5b77d6cc581f487f98fdf8f651e11c4e.wav","peopleName":"我是领导","isDelete":0,"joinTime":null,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541574275000,"updateDate":null,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"","status":1,"num1":"3","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP领导","roleId":45,"departmentName":"黑龙江护路办/一级","group":null},{"id":106,"userName":"HL1306","password":"123456","userPic":"/upload/df0fbd3184494f568c3d8f331549f060.wav","peopleName":"王树春","isDelete":0,"joinTime":null,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541406948000,"updateDate":null,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"44","status":1,"num1":"17","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":105,"userName":"HL1428","password":"123456","userPic":"/upload/89a0426a217a4dc584331c1977f0e43f.wav","peopleName":"郑亚男","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402462000,"updateDate":1541405589000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"42","status":1,"num1":"18","num2":null,"num3":null,"num4":"1","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":104,"userName":"HL1427","password":"123456","userPic":"/upload/af2dc3d21c144945a2b60a8c2b3a700a.wav","peopleName":"边海瑞","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402410000,"updateDate":1541405649000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"21","status":1,"num1":"18","num2":null,"num3":null,"num4":"1","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":103,"userName":"HL1426","password":"123456","userPic":"/upload/14384633c2394fde8eea864aa24be04a.wav","peopleName":"吴明立","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402386000,"updateDate":1541408889000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"35","status":1,"num1":"18","num2":null,"num3":null,"num4":"1","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":102,"userName":"HL1425","password":"123456","userPic":"/upload/dfdfa01336b043719a38b58e9521532f.wav","peopleName":"杜秋","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402356000,"updateDate":1541405814000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"32","status":1,"num1":"18","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":101,"userName":"HL1424","password":"123456","userPic":"/upload/e2de36dc3ada47938f0c37275237c82d.wav","peopleName":"李海超","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402308000,"updateDate":1541405918000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"37","status":1,"num1":"18","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":100,"userName":"HL1423","password":"123456","userPic":"/upload/6e78d5e6035c4c8f87d6e941ea979afb.wav","peopleName":"朱凯","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402259000,"updateDate":1541405953000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"28","status":1,"num1":"18","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":99,"userName":"HL1422","password":"123456","userPic":"/upload/8c98f1bfd4dc4093bcf82dcbe98bbf4a.wav","peopleName":"吴洪涛","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402218000,"updateDate":1541406000000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"42","status":1,"num1":"18","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP领导","roleId":45,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":98,"userName":"HL1421","password":"123456","userPic":"/upload/07b669bc89194ff88a3ece15f688b0af.wav","peopleName":"陈超","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402164000,"updateDate":1541406068000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"43","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":97,"userName":"HL1420","password":"123456","userPic":"/upload/0f2affd30aaa424681c165c0390939cd.wav","peopleName":"于忠","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402121000,"updateDate":1541406106000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"46","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":96,"userName":"HL1419","password":"123456","userPic":"/upload/e79c077676434b3cb25287a13084be70.wav","peopleName":"胡力瑞","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541402083000,"updateDate":1541406153000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"29","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":95,"userName":"HL1418","password":"123456","userPic":"/upload/34782cb76d5e4a108d7fcfea21335ea8.wav","peopleName":"秦彦胜","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401998000,"updateDate":1541406185000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"29","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":94,"userName":"HL1417","password":"123456","userPic":"/upload/4e9fd2a8afc84555b334a3b7ba17dcda.wav","peopleName":"邵红军","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401880000,"updateDate":1541406245000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"39","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":93,"userName":"HL1416","password":"123456","userPic":"/upload/11e270a42a054ad4b6f5d56ea84a5919.wav","peopleName":"张兆明","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401814000,"updateDate":1541406275000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"31","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":92,"userName":"HL1415","password":"123456","userPic":"/upload/f69ecfc3df084aa49f4a1e348adf58fa.wav","peopleName":"边海涛","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401760000,"updateDate":1541406340000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"25","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":91,"userName":"HL1414","password":"123456","userPic":"/upload/716f23b8d86b402db2a1e6baf60e116e.wav","peopleName":"任重阳","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401723000,"updateDate":1541406391000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"36","status":1,"num1":"18","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"肇东市铁路护路联防中队/二级","group":null},{"id":90,"userName":"HL1314","password":"123456","userPic":"/upload/d6a3c7979cf4430a984c2b89a6c42d8b.wav","peopleName":"赵利军","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401240000,"updateDate":1541406460000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"44","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":89,"userName":"HL1311","password":"123456","userPic":"/upload/eae6a38ea82b4171b4102f2b2191746d.wav","peopleName":"范闻铭","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401193000,"updateDate":1541406486000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"29","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":88,"userName":"HL1310","password":"123456","userPic":"/upload/52b4321e21564bc6b7083b70fa284912.wav","peopleName":"陈建","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401124000,"updateDate":1541406541000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"44","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":87,"userName":"HL1305","password":"123456","userPic":"/upload/aedca609dccc43eda75d5b6c9bd8d9cb.wav","peopleName":"曹洪会","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401067000,"updateDate":1541406573000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"41","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":86,"userName":"HL1302","password":"123456","userPic":"/upload/df9b2b0926a74cbda2739101583e4bec.wav","peopleName":"赵德江","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541401032000,"updateDate":1541406614000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"44","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":85,"userName":"HL1313","password":"123456","userPic":"/upload/b49759a417a740339d6390bf848ce6d6.wav","peopleName":"张胜","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400973000,"updateDate":1541406648000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"31","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":84,"userName":"HL1307","password":"123456","userPic":"/upload/e162c6cf06704b029ab57137ada5a294.wav","peopleName":"王建秋","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400892000,"updateDate":1541406674000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"34","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":83,"userName":"HL1304","password":"123456","userPic":"/upload/5fd370a69c8d4e9f906c5a84d21226c8.wav","peopleName":"秦立明","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400838000,"updateDate":1541406735000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"28","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":82,"userName":"HL1303","password":"123456","userPic":"/upload/4c530940c5af4750bcbf2032cd5483e4.wav","peopleName":"卢静","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400788000,"updateDate":1541406768000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"43","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":81,"userName":"HL1312","password":"123456","userPic":"/upload/4ea8cc59fab44f5fadebb992b27968f8.wav","peopleName":"刘峥","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400709000,"updateDate":1541406835000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"25","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":80,"userName":"HL1309","password":"123456","userPic":"/upload/a08563b455e84afd9754ca84ebee17d0.wav","peopleName":"董勋","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400563000,"updateDate":1541406874000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"44","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":79,"userName":"HL1315","password":"123456","userPic":"/upload/50585bd7b12f4c0aaf278250c426cd44.wav","peopleName":"马超","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400456000,"updateDate":1541407000000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"26","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":78,"userName":"HL1308","password":"123456","userPic":"/upload/05107d2aac5c4d46babcc7fa3eea6b4b.wav","peopleName":"于明","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400336000,"updateDate":1541407023000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"50","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":77,"userName":"HL1301","password":"123456","userPic":"/upload/3e50916527464aa69cb15ea6dd232fa5.wav","peopleName":"刘力辉","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541400233000,"updateDate":1541407077000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"49","status":1,"num1":"17","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP领导","roleId":45,"departmentName":"杜尔伯特蒙古族自治县铁路护路联防中队/二级","group":null},{"id":76,"userName":"HL1432","password":"123456","userPic":"/upload/e7d1c522a4204ddbba91c1672e5b6ae9.wav","peopleName":"孙冬","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541398749000,"updateDate":1541407115000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"25","status":1,"num1":"16","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":75,"userName":"HL1430","password":"123456","userPic":"/upload/6c53e11d748e4a19bb3cae418c0d24ca.wav","peopleName":"徐洋","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541390119000,"updateDate":1541407142000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"22","status":1,"num1":"16","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":74,"userName":"HL1439","password":"123456","userPic":"/upload/d5e7e6afcf6942d68a22a76fb1532db1.wav","peopleName":"陈鑫","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541390016000,"updateDate":1541407181000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"23","status":1,"num1":"16","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":73,"userName":"HL1437","password":"123456","userPic":"/upload/3470b933896046c3a7f13e89aca004ea.wav","peopleName":"武冠宇","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389870000,"updateDate":1541407206000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"23","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":72,"userName":"HL1438","password":"123456","userPic":"/upload/f1dd74e57ad943f3aaf888d41b7bdc1d.wav","peopleName":"徐奇","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389725000,"updateDate":1541407232000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"25","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":71,"userName":"HL1431","password":"123456","userPic":"/upload/93cb84b9a4b642f7b8f697dd42a1ac81.wav","peopleName":"冯楠","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389550000,"updateDate":1541407264000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"27","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":70,"userName":"HL1435","password":"123456","userPic":"/upload/c926c59c306b4825af104c9e11646057.wav","peopleName":"孙建","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389421000,"updateDate":1541407291000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"33","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":69,"userName":"HL1436","password":"123456","userPic":"/upload/3f07d3275fbf49cc95bace2644ad0423.wav","peopleName":"张卫","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389308000,"updateDate":1541407316000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"36","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":68,"userName":"HL1434","password":"123456","userPic":"/upload/a536e89ed34d47d09869aa55042cb580.wav","peopleName":"张邵宇","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389164000,"updateDate":1541407342000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"43","status":1,"num1":"16","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":67,"userName":"HL1433","password":"123456","userPic":"/upload/4fab5d78d7cb40b181861d8ffe5bccf5.wav","peopleName":"孙令江","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541389065000,"updateDate":1541407365000,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"43","status":1,"num1":"16","num2":null,"num3":null,"num4":null,"num5":"","roleName":"APP护路员","roleId":44,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":66,"userName":"HL1429","password":"123456","userPic":"/upload/5c9c877edb52401fb4e9b8aa0cd7b9ea.wav","peopleName":"钱守伟","isDelete":0,"joinTime":0,"phonenum":null,"address":"","sex":1,"lastTime":null,"createDate":1541388810000,"updateDate":1541405404000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"32","status":1,"num1":"16","num2":null,"num3":null,"num4":"","num5":"","roleName":"APP领导","roleId":45,"departmentName":"安达市铁路护路联防中队/二级","group":null},{"id":65,"userName":"hero","password":"3588","userPic":"/upload/b74fefe47c14489385ef9788b91c2bf0.wav","peopleName":"测试护路员02","isDelete":0,"joinTime":1541376000000,"phonenum":null,"address":"啊啊","sex":1,"lastTime":null,"createDate":1541382650000,"updateDate":null,"loginStutas":0,"createBy":"后台_黑龙江_01","userAge":"23","status":1,"num1":"5","num2":null,"num3":null,"num4":"","num5":"11111","roleName":"护路员","roleId":47,"departmentName":"道里区护路办/三级","group":null},{"id":63,"userName":"ld","password":"123456","userPic":"/upload/490aae803b784645be589d5d0e244538.jpg","peopleName":"测试领导","isDelete":0,"joinTime":1538352000000,"phonenum":null,"address":"哈尔滨市南岗区","sex":1,"lastTime":null,"createDate":1540792608000,"updateDate":null,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"40","status":1,"num1":"4","num2":null,"num3":null,"num4":"","num5":"13121880086","roleName":"APP领导","roleId":45,"departmentName":"哈尔滨护路办/二级","group":null},{"id":62,"userName":"hly","password":"123456","userPic":"/upload/52cc650374514f4d8f4d6913172e3597.jpg","peopleName":"测试护路员","isDelete":0,"joinTime":1540771200000,"phonenum":null,"address":"哈尔滨市南岗区","sex":1,"lastTime":null,"createDate":1540792449000,"updateDate":1541242235000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"28","status":1,"num1":"6","num2":null,"num3":null,"num4":"","num5":"15244753606","roleName":"护路员","roleId":47,"departmentName":"南岗区护路办/三级","group":null},{"id":1,"userName":"admin","password":"admin","userPic":"/upload/695124db36564c03b94c78def019940c.jpg","peopleName":"后台_黑龙江_01","isDelete":0,"joinTime":1537920000000,"phonenum":null,"address":"110","sex":1,"lastTime":null,"createDate":null,"updateDate":1540470886000,"loginStutas":1,"createBy":"后台_黑龙江_01","userAge":"110","status":1,"num1":"3","num2":"101012","num3":null,"num4":"","num5":"13045134573","roleName":"超级无敌管理员","roleId":1,"departmentName":"黑龙江护路办/一级","group":null}]
     * totalPage : null
     * totalCount : null
     */

    private String status;
    private Object errorCode;
    private Object errorTitle;
    private Object errorMsg;
    private Object totalPage;
    private Object totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(Object errorTitle) {
        this.errorTitle = errorTitle;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public Object getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Object totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 108
         * userName : gm
         * password : 123
         * userPic : null
         * peopleName : 后台测试员
         * isDelete : 0
         * joinTime : 1542758400000
         * phonenum : null
         * address :
         * sex : 1
         * lastTime : null
         * createDate : 1542787396000
         * updateDate : 1542937159000
         * loginStutas : 1
         * createBy : 后台_黑龙江_01
         * userAge : 10
         * status : 1
         * num1 : 15
         * num2 : null
         * num3 : null
         * num4 : null
         * num5 :
         * roleName : 超级无敌管理员
         * roleId : 1
         * departmentName : 鹤岗护路办/二级
         * group : null
         */

        private int id;
        private String userName;
        private String password;
        private Object userPic;
        private String peopleName;
        private int isDelete;
        private long joinTime;
        private Object phonenum;
        private String address;
        private int sex;
        private Object lastTime;
        private long createDate;
        private long updateDate;
        private int loginStutas;
        private String createBy;
        private String userAge;
        private int status;
        private String num1;
        private Object num2;
        private Object num3;
        private Object num4;
        private String num5;
        private String roleName;
        private int roleId;
        private String departmentName;
        private Object group;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getUserPic() {
            return userPic;
        }

        public void setUserPic(Object userPic) {
            this.userPic = userPic;
        }

        public String getPeopleName() {
            return peopleName;
        }

        public void setPeopleName(String peopleName) {
            this.peopleName = peopleName;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public long getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(long joinTime) {
            this.joinTime = joinTime;
        }

        public Object getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(Object phonenum) {
            this.phonenum = phonenum;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getLastTime() {
            return lastTime;
        }

        public void setLastTime(Object lastTime) {
            this.lastTime = lastTime;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public int getLoginStutas() {
            return loginStutas;
        }

        public void setLoginStutas(int loginStutas) {
            this.loginStutas = loginStutas;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUserAge() {
            return userAge;
        }

        public void setUserAge(String userAge) {
            this.userAge = userAge;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getNum1() {
            return num1;
        }

        public void setNum1(String num1) {
            this.num1 = num1;
        }

        public Object getNum2() {
            return num2;
        }

        public void setNum2(Object num2) {
            this.num2 = num2;
        }

        public Object getNum3() {
            return num3;
        }

        public void setNum3(Object num3) {
            this.num3 = num3;
        }

        public Object getNum4() {
            return num4;
        }

        public void setNum4(Object num4) {
            this.num4 = num4;
        }

        public String getNum5() {
            return num5;
        }

        public void setNum5(String num5) {
            this.num5 = num5;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public Object getGroup() {
            return group;
        }

        public void setGroup(Object group) {
            this.group = group;
        }
    }
}
