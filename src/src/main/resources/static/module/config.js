/**
 * crab 1.0
 * http://ant.baomidou.com
 */
layui.define(function (exports) {

    var config = {
        base_server: '', // 接口地址
        theme: 'theme-blue-white',  // 主题颜色
        audioId: 'ant-audio',  // 音频 ID
        audioMsg: '/assets/images/msg.mp3',  // 音频消息
        autoRender: false,  // 窗口大小改变后是否自动重新渲染表格
        side: true,   // 是否开启侧栏
        pageTabs: false,   // 是否开启多标签
        // 多语言提示
        lang: {
            deleteRow: '该行记录已成功删除！',
            unlockTip: '请输入登录密码解锁',
            unlockBtn: '解锁'
        },
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.data.total,
                "data": res.data.records
            };
        }
    };
    exports('config', config);
});
