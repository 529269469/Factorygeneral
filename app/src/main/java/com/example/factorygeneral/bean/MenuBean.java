package com.example.factorygeneral.bean;

import java.util.List;

/**
 * @author :created by ${ WYW }
 * 时间：2019/10/27 14
 */
public class MenuBean {


    /**
     * menusList : [{"id":"174","keyId":"15725868551304362","name":"详细信息","userName":"admin","uuid":"15725753414253188"},{"id":"175","keyId":"15725868683268603","name":"验收申请","userName":"admin","uuid":"15725753414253188"},{"id":"176","keyId":"15725868857098982","name":"验收任务单","userName":"admin","uuid":"15725753414253188"},{"id":"177","keyId":"15725869066547373","name":"齐套性检查","userName":"admin","uuid":"15725753414253188"},{"id":"178","keyId":"15725869166868530","name":"过程检查","userName":"admin","uuid":"15725753414253188"},{"id":"179","keyId":"15725869283981091","name":"技术类检查","userName":"admin","uuid":"15725753414253188"},{"id":"180","keyId":"15725869425743443","name":"验收结论","userName":"admin","uuid":"15725753414253188"},{"id":"181","keyId":"15725869717989008","name":"验收遗留问题落实","userName":"admin","uuid":"15725753414253188"},{"id":"182","keyId":"15725869913979812","name":"交付清单","userName":"admin","uuid":"15725753414253188"}]
     * moduleList : [{"id":"239","keyId":"15725868551304362","keyUuid":"15725870445445701","name":"详细信息","userName":"admin"},{"id":"240","keyId":"15725868683268603","keyUuid":"15725874553973348","name":"验收申请","userName":"admin"},{"id":"241","keyId":"15725868857098982","keyUuid":"15725897002851458","name":"验收任务单","userName":"admin"},{"id":"242","keyId":"15725869066547373","keyUuid":"15725921988346003","name":"依据文件检查","userName":"admin"},{"id":"243","keyId":"15725869066547373","keyUuid":"15725922126453497","name":"产品齐套性检查","userName":"admin"},{"id":"244","keyId":"15725869166868530","keyUuid":"15728594372652191","name":"原材料、标准件检查","userName":"admin"},{"id":"245","keyId":"15725869166868530","keyUuid":"15728595418838503","name":"产品生产前","userName":"admin"},{"id":"246","keyId":"15725869166868530","keyUuid":"15728596516336532","name":"产品生产过程","userName":"admin"},{"id":"247","keyId":"15725869166868530","keyUuid":"15728597184116538","name":"产品质量问题归零情况","userName":"admin"},{"id":"248","keyId":"15725869166868530","keyUuid":"15728598370573973","name":"检查结论","userName":"admin"},{"id":"249","keyId":"15725869283981091","keyUuid":"15729191146617764","name":"技术状态检查","userName":"admin"},{"id":"250","keyId":"15725869283981091","keyUuid":"15729191326303363","name":"产品外观检查","userName":"admin"},{"id":"251","keyId":"15725869283981091","keyUuid":"15729191503436914","name":"产品尺寸检查","userName":"admin"},{"id":"252","keyId":"15725869283981091","keyUuid":"15729191729794389","name":"筛查试验报告及记录","userName":"admin"},{"id":"253","keyId":"15725869283981091","keyUuid":"15729191896805548","name":"包装检查","userName":"admin"},{"id":"254","keyId":"15725869283981091","keyUuid":"15729192137931465","name":"质量证明文件签署","userName":"admin"},{"id":"255","keyId":"15725869283981091","keyUuid":"15729192244154374","name":"检查结论","userName":"admin"},{"id":"256","keyId":"15725869425743443","keyUuid":"15729225171316303","name":"检查结论","userName":"admin"},{"id":"257","keyId":"15725869717989008","keyUuid":"15729231203664171","name":"验收遗留问题落实情况","userName":"admin"}]
     * projectModel : {"id":"51","name":"下厂验收APP","userName":"admin","uuid":"15725753414253188"}
     * time : 1572925377967
     * unitList : [{"answer":"","content":"","contentFile":"","id":"392","keyUuid":"15725870445445701","label":"编号%%&@名称%%&@创建者%%&@创建时间%%&@责任单位%%&@类型%%&@产品名称%%&@产品类别%%&@型号代号%%&@批次","relevantFile":"产品验收详细信息.docx@%%%@20f6b719c5824ae2a03f7cff1a32af5c.docx,001.jpg@%%%@3ab4098c9fa44859b83b864423e4afac.jpg","sx":"1","text":"C021%%&@XX单位遥安分系统验收数据包%%&@申工%%&@2019-04-25  9:45:34%%&@699厂%%&@产品验收数据包%%&@单机%%&@电气产品%%&@C","type":"textBox","userName":"admin"},{"answer":"10%%&@10%%&@8%%&@10%%&@8%%&@4%%&@10%%&@4%%&@4%%&@6%%&@6%%&@6%%&@10","content":"HT-10,遥安分系统生产合同,XXX1-10,遥安分系统,XXX1-10,C,10/3/4,是,是,是,是,是,%%&@null,null,null,null,null,null,null,null,null,null,null,null,null","contentFile":"文字图片表格 (1).docx@%%%@0c015ecaafcf4995906d15befe267b96.docx,002.jpg@%%%@ebfa89912c0540e0a5098f40b09c42f1.jpg%%&@null","id":"393","keyUuid":"15725874553973348","label":"验收表单","relevantFile":"","sx":"1","text":"合同编号%%&@合同名称%%&@产品代号%%&@产品名称%%&@产品编号%%&@产品状态%%&@投产数量/已验收数据/验收数据%%&@是否终检%%&@是否军检%%&@帅选试验是否完成%%&@例行试验是否完成%%&@是否具备出厂交付条件%%&@备注","type":"table","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"394","keyUuid":"15725874553973348","label":"申请单位%%&@申请人%%&@联系电话","relevantFile":"","sx":"2","text":"","type":"textBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"395","keyUuid":"15725874553973348","label":"验收内部审查结论","relevantFile":"内部审查结论.docx@%%%@1c04df37ebe44665b79d382946b94c2a.docx,003.jpg@%%%@e840e286ab544726a4913c67f9d7a7c1.jpg,001.mp4@%%%@df8042b8425d4b65a22b0f2218e522d8.mp4","sx":"3","text":"","type":"input","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"396","keyUuid":"15725874553973348","label":"备注","relevantFile":"","sx":"4","text":"","type":"input","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"397","keyUuid":"15725897002851458","label":"签发人%%&@签发部门%%&@产品接受人%%&@产品接受时间","relevantFile":"","sx":"1","text":"张总%%&@型号办%%&@七院%%&@2019-12-12","type":"textBox","userName":"admin"},{"answer":"10%%&@10%%&@8%%&@10%%&@8%%&@4%%&@10%%&@4%%&@4%%&@6%%&@6%%&@6%%&@10","content":"HT-10,遥安分系统生产合同,XXX1-10,遥安分系统,XXX1-10,C,10/3/4,是,是,是,是,是,","contentFile":"遥安分系统生产合同.docx@%%%@574d1942fc624aabaa7fd0f3525312d6.docx","id":"398","keyUuid":"15725897002851458","label":"验收表单","relevantFile":"","sx":"2","text":"合同编号%%&@合同名称%%&@产品代号%%&@产品名称%%&@产品编号%%&@产品状态%%&@投产数量/已验收数量/验收数量%%&@是否终检%%&@是否军检%%&@帅选试验是否完成%%&@例行试验是否完成%%&@是否具备出厂交付条件%%&@备注","type":"table","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"399","keyUuid":"15725897002851458","label":"申请单位%%&@申请人%%&@联系电话","relevantFile":"012.jpg@%%%@68a27fc5b27748ab995e67feda759bab.jpg","sx":"3","text":"12所%%&@夏总%%&@010-85782568","type":"textBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"400","keyUuid":"15725897002851458","label":"验收部门%%&@验收人%%&@验收时间","relevantFile":"","sx":"4","text":"12所%%&@夏总%%&@2019-10-10","type":"textBox","userName":"admin"},{"answer":"6%%&@8%%&@10%%&@15%%&@4%%&@8%%&@10%%&@4%%&@10%%&@10","content":"1,合同,XX-C021-001JY,遥安分系统生产合同,C,王总,2018-12-12,是,合格,%%&@3,任务书,XX-C201-013WR,遥安分系统生产任务书,C,李总,2018-05-06,是,合格,null%%&@2,明细表,XX-C021-012MX,遥安分系统配套明细表,C,李总,2018-111-16,是,合格,null","contentFile":"遥安分系统生产合同.docx@%%%@0445f7ad840c41c9b685cbcef9b9811f.docx%%&@遥安分系统生产任务书.docx@%%%@b772101a5b0c44c9a4f8d937e99252f2.docx%%&@遥安分系统配套明细表.xlsx@%%%@6a4b1f9a0f9f46728faf9de856a66f80.xlsx","id":"401","keyUuid":"15725921988346003","label":"管理文件齐套性","relevantFile":"","sx":"1","text":"序号%%&@文件类别%%&@文件代号%%&@文件名称%%&@技术状态%%&@批准人%%&@批准日期%%&@是否晒蓝%%&@验收结论%%&@备注","type":"table","userName":"admin"},{"answer":"6%%&@8%%&@10%%&@15%%&@4%%&@8%%&@10%%&@4%%&@10%%&@10","content":"1,技术要求,null,null,null,null,null,null,null,null%%&@2,技术条件,null,null,null,null,null,null,null,null%%&@3,验收大纲,null,null,null,null,null,null,null,null%%&@4,校定细则,null,null,null,null,null,null,null,null%%&@5,验收细则,null,null,null,null,null,null,null,null%%&@6,设计图样,null,null,null,null,null,null,null,null","contentFile":"null%%&@null%%&@null%%&@null%%&@null%%&@null","id":"402","keyUuid":"15725921988346003","label":"技术文件齐套性","relevantFile":"","sx":"2","text":"序号%%&@文件类别%%&@文件代号%%&@文件名称%%&@技术状态%%&@批准人%%&@批准日期%%&@是否晒蓝%%&@验收结论%%&@备注","type":"table","userName":"admin"},{"answer":"","id":"403","keyUuid":"15725922126453497","label":"例行产品是否接受","relevantFile":"","sx":"1","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"404","keyUuid":"15725922126453497","label":"例行试验产品数量%%&@例行试验产品编号","relevantFile":"","sx":"2","text":"","type":"textBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"405","keyUuid":"15725922126453497","label":"实际验收产品与配套明细表是否一致","relevantFile":"","sx":"3","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"406","keyUuid":"15725922126453497","label":"有无备件","relevantFile":"","sx":"4","text":"有&&无","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"407","keyUuid":"15725922126453497","label":"备件的项目、数量是否符配套明细表要求","relevantFile":"","sx":"5","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"408","keyUuid":"15725922126453497","label":"检查结论","relevantFile":"","sx":"6","text":"","type":"input","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"409","keyUuid":"15725922126453497","label":"检查人签字","relevantFile":"","sx":"7","text":"003.jpg@%%%@3414211d6f0a47cbb5395a04e549c9a3.jpg","type":"autograph","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"410","keyUuid":"15728594372652191","label":"原材料是否进行复验","relevantFile":"原材料复验记录.docx@%%%@b5c9d59c06db4f9fbd8381f448d53cb1.docx,004.jpg@%%%@f6c84ddd65bf44bebe867ad930dea10c.jpg,汽车起步流程.mp4@%%%@83a5b04c75034bfb9ab372c79e576441.mp4,原材料复验登记表.xlsx@%%%@22ac4c3abb514c43a7fb39d6b662462f.xlsx","sx":"1","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"411","keyUuid":"15728594372652191","label":"复验记录是否完整有效","relevantFile":"","sx":"2","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"412","keyUuid":"15728594372652191","label":"原材料是否有技术合格证","relevantFile":"","sx":"3","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"413","keyUuid":"15728594372652191","label":"无技术合格证的原材料是否鉴定","relevantFile":"","sx":"4","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"414","keyUuid":"15728594372652191","label":"鉴定结论","relevantFile":"","sx":"5","text":"合格&&不合格&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"415","keyUuid":"15728594372652191","label":"复验记录是否完整","relevantFile":"","sx":"6","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"416","keyUuid":"15728594372652191","label":"有无代料","relevantFile":"","sx":"7","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"417","keyUuid":"15728594372652191","label":"代料是否办理手续","relevantFile":"","sx":"8","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"418","keyUuid":"15728595418838503","label":"验收文件是否确认有效","relevantFile":"","sx":"1","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"419","keyUuid":"15728595418838503","label":"是否方案评审及甲方人员参加","relevantFile":"","sx":"2","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"420","keyUuid":"15728595418838503","label":"是否转阶段评审及甲方人员参加","relevantFile":"","sx":"3","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"421","keyUuid":"15728595418838503","label":"是否生产前对设计过程进行确认和记录","relevantFile":"","sx":"4","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"422","keyUuid":"15728595418838503","label":"是否生产前对工艺过程质量进行确认和记录","relevantFile":"","sx":"5","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"423","keyUuid":"15728595418838503","label":"有无确认和记录","relevantFile":"","sx":"6","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"424","keyUuid":"15728595418838503","label":"是否进行生产准备状态检查","relevantFile":"","sx":"7","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"425","keyUuid":"15728595418838503","label":"有无检查记录","relevantFile":"","sx":"8","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"426","keyUuid":"15728595418838503","label":"首检是否鉴定","relevantFile":"","sx":"9","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"427","keyUuid":"15728595418838503","label":"甲方人员是否参加鉴定","relevantFile":"","sx":"10","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"428","keyUuid":"15728596516336532","label":"首件是否鉴定","relevantFile":"","sx":"1","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"429","keyUuid":"15728596516336532","label":"生产检测用设备、量具是否在使用有效期内","relevantFile":"","sx":"2","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"430","keyUuid":"15728596516336532","label":"甲方人员是否参加鉴定","relevantFile":"","sx":"3","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"431","keyUuid":"15728596516336532","label":"有无外包（外协）产品","relevantFile":"","sx":"4","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"432","keyUuid":"15728596516336532","label":"外包（外协）产品是否验收","relevantFile":"","sx":"5","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"433","keyUuid":"15728596516336532","label":"外包（外协）产品验收记录是否完整有效","relevantFile":"","sx":"6","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"434","keyUuid":"15728596516336532","label":"是否有不合格品审理","relevantFile":"","sx":"7","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"435","keyUuid":"15728596516336532","label":"不合格品处置措施是否落实","relevantFile":"","sx":"8","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"436","keyUuid":"15728596516336532","label":"不合格品处置单审理的结论","relevantFile":"","sx":"9","text":"返工&&返修&&超差使用&&报废","type":"checkBox","userName":"admin"},{"answer":"","id":"437","keyUuid":"15728596516336532","label":"过程记录是否完整有效","relevantFile":"","sx":"10","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"438","keyUuid":"15728596516336532","label":"调试记录是否完整有效","relevantFile":"","sx":"11","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"439","keyUuid":"15728596516336532","label":"多余物控制记录是否符合要求","relevantFile":"","sx":"12","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"440","keyUuid":"15728596516336532","label":"关键件、重要件及关键工序、特殊过程的加工记录是否完整","relevantFile":"","sx":"13","text":"符合要求&&不符合要求","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"441","keyUuid":"15728596516336532","label":"导电氧化导电测试记录\u2014\u2014要求值%%&@导电氧化导电测试记录\u2014\u2014测量值%%&@导电氧化导电测试记录\u2014\u2014结论","relevantFile":"","sx":"14","text":"","type":"textBox","userName":"admin"},{"id":"442","keyUuid":"15728596516336532","label":"绝缘阳极氧化绝缘测试记录\u2014\u2014要求值%%&@绝缘阳极氧化绝缘测试记录\u2014\u2014测量值%%&@绝缘阳极氧化绝缘测试记录\u2014\u2014结论","relevantFile":"","sx":"15","text":"","type":"textBox","userName":"admin"},{"answer":"","id":"443","keyUuid":"15728596516336532","label":"技术（工艺）通知单","relevantFile":"","sx":"16","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"444","keyUuid":"15728596516336532","label":"技术（工艺）通知单是否落实（附汇总表）","relevantFile":"","sx":"17","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"445","keyUuid":"15728596516336532","label":"设计（工艺）更改单","relevantFile":"","sx":"18","text":"有&&无&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"446","keyUuid":"15728596516336532","label":"设计（工艺）更改单是否落实（附汇总表）","relevantFile":"","sx":"19","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"id":"447","keyUuid":"15728596516336532","label":"特殊过程记录及其它","relevantFile":"","sx":"20","text":"","type":"input","userName":"admin"},{"id":"448","keyUuid":"15728597184116538","label":"产品质量问题归零情况","relevantFile":"","sx":"1","text":"","type":"textBox","userName":"admin"},{"answer":"","id":"449","keyUuid":"15728597184116538","label":"技术问题是否归零","relevantFile":"","sx":"2","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"450","keyUuid":"15728597184116538","label":"管理问题是否归零","relevantFile":"","sx":"3","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"451","keyUuid":"15728597184116538","label":"归零结论","relevantFile":"","sx":"4","text":"有&&无","type":"checkBox","userName":"admin"},{"answer":"","id":"452","keyUuid":"15728597184116538","label":"归零措施是否落实","relevantFile":"","sx":"5","text":"是&&否","type":"checkBox","userName":"admin"},{"id":"453","keyUuid":"15728598370573973","label":"过程检查结论","relevantFile":"","sx":"1","text":"","type":"input","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"454","keyUuid":"15728598370573973","label":"检查人签字","relevantFile":"","sx":"2","text":"005.jpg@%%%@f4db7618197c4820bd246f109eede63a.jpg","type":"autograph","userName":"admin"},{"answer":"","id":"455","keyUuid":"15729191146617764","label":"技术通知单","relevantFile":"","sx":"1","text":"有&&无","type":"checkBox","userName":"admin"},{"answer":"","id":"456","keyUuid":"15729191146617764","label":"技术通知单是否落实","relevantFile":"","sx":"2","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"457","keyUuid":"15729191146617764","label":"设计更改单","relevantFile":"","sx":"3","text":"有&&无","type":"checkBox","userName":"admin"},{"answer":"","id":"458","keyUuid":"15729191146617764","label":"设计更改单是否落实","relevantFile":"","sx":"4","text":"是&&否","type":"checkBox","userName":"admin"},{"id":"459","keyUuid":"15729191326303363","label":"特殊记录及其它","relevantFile":"","sx":"1","text":"","type":"input","userName":"admin"},{"id":"460","keyUuid":"15729191326303363","label":"产品外观检查结论","relevantFile":"","sx":"2","text":"","type":"input","userName":"admin"},{"answer":"","id":"461","keyUuid":"15729191326303363","label":"检查人签字","relevantFile":"","sx":"3","text":"","type":"autograph","userName":"admin"},{"id":"462","keyUuid":"15729191326303363","label":"检查人签字日期","relevantFile":"","sx":"4","text":"","type":"textBox","userName":"admin"},{"answer":"10%%&@20%%&@30%%&@10%%&@10%%&@10","content":"1,null,null,null,null,null","contentFile":"null","id":"463","keyUuid":"15729191503436914","label":"尺寸检查","relevantFile":"","sx":"1","text":"序号%%&@检查项目%%&@技术条件规定%%&@验收时实际值%%&@验收结果%%&@备注","type":"table","userName":"admin"},{"answer":"10%%&@20%%&@20%%&@10%%&@20%%&@20","content":"1,万用表,WYB00123,0.001,2020-12-12,","contentFile":"万用表合格证明.docx@%%%@5d63608c0838482abeee5cf54333a084.docx","id":"464","keyUuid":"15729191503436914","label":"使用量具","relevantFile":"","sx":"2","text":"序号%%&@名称%%&@型号规格%%&@精度%%&@合格证和有效期%%&@备注","type":"table","userName":"admin"},{"answer":"","id":"465","keyUuid":"15729191729794389","label":"被筛选的产品是否与验收产品的型号、批次相同","relevantFile":"","sx":"1","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"466","keyUuid":"15729191729794389","label":"金属材料拉力试验%%&@焊接接头拉伸试验%%&@对接焊缝、焊接接头X射线检查%%&@机械性能检查%%&@铝合金锻件超声波探伤检查%%&@气密性检查%%&@起吊试验%%&@淋雨试验%%&@公路运输试验","relevantFile":"","sx":"2","text":"","type":"textBox","userName":"admin"},{"id":"467","keyUuid":"15729191729794389","label":"其他试验情况","relevantFile":"","sx":"3","text":"","type":"input","userName":"admin"},{"answer":"","id":"468","keyUuid":"15729191729794389","label":"试验用仪表是否在使用有效期内","relevantFile":"","sx":"4","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"469","keyUuid":"15729191729794389","label":"试验用设备鉴定期是否符合规定","relevantFile":"","sx":"5","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"id":"470","keyUuid":"15729191729794389","label":"特殊情况记载及其它","relevantFile":"","sx":"6","text":"","type":"input","userName":"admin"},{"id":"471","keyUuid":"15729191729794389","label":"验收结论","relevantFile":"","sx":"7","text":"","type":"input","userName":"admin"},{"answer":"","id":"472","keyUuid":"15729191729794389","label":"检查人签名","relevantFile":"","sx":"8","text":"","type":"autograph","userName":"admin"},{"id":"473","keyUuid":"15729191729794389","label":"检查日期","relevantFile":"","sx":"9","text":"","type":"textBox","userName":"admin"},{"answer":"","id":"474","keyUuid":"15729191896805548","label":"包装箱防护与标识是否符合要求","relevantFile":"","sx":"1","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"475","keyUuid":"15729191896805548","label":"有无装箱清单","relevantFile":"","sx":"2","text":"有&&无","type":"checkBox","userName":"admin"},{"answer":"","id":"476","keyUuid":"15729191896805548","label":"有无备件","relevantFile":"","sx":"3","text":"是&&否","type":"checkBox","userName":"admin"},{"answer":"","id":"477","keyUuid":"15729191896805548","label":"产品包装是否符合要求","relevantFile":"","sx":"4","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"478","keyUuid":"15729191896805548","label":"包装箱内产品是否与验收产品一致","relevantFile":"","sx":"5","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"479","keyUuid":"15729191896805548","label":"包装箱内产品及附件是否与包装箱清单一致","relevantFile":"","sx":"6","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"id":"480","keyUuid":"15729191896805548","label":"特殊记载及其它","relevantFile":"","sx":"7","text":"","type":"input","userName":"admin"},{"answer":"","id":"481","keyUuid":"15729191896805548","label":"检查人签字","relevantFile":"","sx":"8","text":"","type":"autograph","userName":"admin"},{"id":"482","keyUuid":"15729191896805548","label":"检查日期","relevantFile":"","sx":"9","text":"","type":"textBox","userName":"admin"},{"answer":"","id":"483","keyUuid":"15729192137931465","label":"产品证明书是否正确","relevantFile":"","sx":"1","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"484","keyUuid":"15729192137931465","label":"合格证状态是否符合要求","relevantFile":"","sx":"2","text":"是&&否&&不涉及","type":"checkBox","userName":"admin"},{"answer":"","id":"485","keyUuid":"15729192137931465","label":"检查人签字","relevantFile":"","sx":"3","text":"","type":"autograph","userName":"admin"},{"id":"486","keyUuid":"15729192137931465","label":"检查日期","relevantFile":"","sx":"4","text":"","type":"textBox","userName":"admin"},{"id":"487","keyUuid":"15729192244154374","label":"检查结论","relevantFile":"","sx":"1","text":"","type":"input","userName":"admin"},{"answer":"","id":"488","keyUuid":"15729192244154374","label":"检查人签字","relevantFile":"","sx":"2","text":"","type":"autograph","userName":"admin"},{"id":"489","keyUuid":"15729192244154374","label":"检查日期","relevantFile":"","sx":"3","text":"","type":"textBox","userName":"admin"},{"id":"490","keyUuid":"15729225171316303","label":"齐套性检查结论","relevantFile":"","sx":"1","text":"","type":"input","userName":"admin"},{"answer":"","content":"","contentFile":"","id":"491","keyUuid":"15729225171316303","label":"过程检查结论","relevantFile":"","sx":"2","text":"","type":"input","userName":"admin"},{"id":"492","keyUuid":"15729225171316303","label":"技术类检查结论","relevantFile":"","sx":"3","text":"","type":"input","userName":"admin"},{"answer":"10%%&@20%%&@30%%&@10","content":"1,null,null,null","contentFile":"null","id":"493","keyUuid":"15729225171316303","label":"产品验收情况","relevantFile":"","sx":"4","text":"序号%%&@产品编号%%&@产品名称%%&@是否通过验收","type":"table","userName":"admin"},{"answer":"10%%&@15%%&@20%%&@30%%&@15","content":"1,null,null,null,null","contentFile":"null","id":"494","keyUuid":"15729225171316303","label":"产品验收遗留问题","relevantFile":"","sx":"5","text":"序号%%&@产品编号%%&@产品名称%%&@遗留问题%%&@遗留问题落实确认人","type":"table","userName":"admin"},{"answer":"","id":"495","keyUuid":"15729225171316303","label":"验收人签字","relevantFile":"","sx":"6","text":"","type":"autograph","userName":"admin"},{"id":"496","keyUuid":"15729225171316303","label":"签字时间","relevantFile":"","sx":"7","text":"","type":"textBox","userName":"admin"},{"answer":"10%%&@15%%&@15%%&@20%%&@15%%&@15%%&@10","content":"1,null,null,null,null,null,null","contentFile":"null","id":"497","keyUuid":"15729231203664171","label":"产品验收遗留问题","relevantFile":"","sx":"1","text":"序号%%&@产品编号%%&@产品名称%%&@遗留问题%%&@遗留问题落实确认人%%&@遗留问题落实时间%%&@遗留问题落实情况说明文件","type":"table","userName":"admin"}]
     * uuid : 15725753414253188
     */

    private ProjectModelBean projectModel;
    private String time;
    private String uuid;
    private List<MenusListBean> menusList;
    private List<ModuleListBean> moduleList;
    private List<UnitListBean> unitList;



    public ProjectModelBean getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModelBean projectModel) {
        this.projectModel = projectModel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<MenusListBean> getMenusList() {
        return menusList;
    }

    public void setMenusList(List<MenusListBean> menusList) {
        this.menusList = menusList;
    }

    public List<ModuleListBean> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ModuleListBean> moduleList) {
        this.moduleList = moduleList;
    }

    public List<UnitListBean> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<UnitListBean> unitList) {
        this.unitList = unitList;
    }

    public static class ProjectModelBean {
        /**
         * id : 51
         * name : 下厂验收APP
         * userName : admin
         * uuid : 15725753414253188
         */

        private String id;
        private String name;
        private String userName;
        private String uuid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    public static class MenusListBean {
        /**
         * id : 174
         * keyId : 15725868551304362
         * name : 详细信息
         * userName : admin
         * uuid : 15725753414253188
         */

        private String id;
        private String keyId;
        private String name;
        private String userName;
        private String uuid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }
    }

    public static class ModuleListBean {
        /**
         * id : 239
         * keyId : 15725868551304362
         * keyUuid : 15725870445445701
         * name : 详细信息
         * userName : admin
         */

        private String id;
        private String keyId;
        private String keyUuid;
        private String name;
        private String userName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyId() {
            return keyId;
        }

        public void setKeyId(String keyId) {
            this.keyId = keyId;
        }

        public String getKeyUuid() {
            return keyUuid;
        }

        public void setKeyUuid(String keyUuid) {
            this.keyUuid = keyUuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class UnitListBean {
        /**
         * answer :
         * content :
         * contentFile :
         * id : 392
         * keyUuid : 15725870445445701
         * label : 编号%%&@名称%%&@创建者%%&@创建时间%%&@责任单位%%&@类型%%&@产品名称%%&@产品类别%%&@型号代号%%&@批次
         * relevantFile : 产品验收详细信息.docx@%%%@20f6b719c5824ae2a03f7cff1a32af5c.docx,001.jpg@%%%@3ab4098c9fa44859b83b864423e4afac.jpg
         * sx : 1
         * text : C021%%&@XX单位遥安分系统验收数据包%%&@申工%%&@2019-04-25  9:45:34%%&@699厂%%&@产品验收数据包%%&@单机%%&@电气产品%%&@C
         * type : textBox
         * userName : admin
         */

        private String answer;
        private String content;
        private String contentFile;
        private String id;
        private String keyUuid;
        private String label;
        private String relevantFile;
        private String sx;
        private String text;
        private String type;
        private String userName;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContentFile() {
            return contentFile;
        }

        public void setContentFile(String contentFile) {
            this.contentFile = contentFile;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyUuid() {
            return keyUuid;
        }

        public void setKeyUuid(String keyUuid) {
            this.keyUuid = keyUuid;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getRelevantFile() {
            return relevantFile;
        }

        public void setRelevantFile(String relevantFile) {
            this.relevantFile = relevantFile;
        }

        public String getSx() {
            return sx;
        }

        public void setSx(String sx) {
            this.sx = sx;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
