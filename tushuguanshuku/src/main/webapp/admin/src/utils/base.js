const base = {
    get() {
        return {
            url : "http://localhost:8080/tushuguanshuku/",
            name: "tushuguanshuku",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/tushuguanshuku/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "图书馆书库管理系统"
        } 
    }
}
export default base
