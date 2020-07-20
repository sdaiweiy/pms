/**
 * crab 1.0
 * http://crab.baomidou.com
 */
layui.define(['config', 'crab', 'layer', 'laytpl', 'element', 'form'], function (exports) {
    var $ = layui.jquery;
    var config = layui.config;
    var crab = layui.crab;
    var layer = layui.layer;
    var laytpl = layui.laytpl;
    var element = layui.element;
    var form = layui.form;
    var crabMenus = 'menus', tabClass = 'layui-this',indexPage = {menuId:'',page:''};

    var index = {
        // 渲染左侧导航栏
        initLeftNav: function (menus, id) {
            // 渲染顶部菜单
            var html = '', tab = 0;
            indexPage.page = crab.getUser().indexPage;
            // 循环选择 Tab
            if (id) {
                $('.tabSwitch,.tabBlank').each(function (i) {
                    var p = $(this).parent();
                    if (id == $(this).data('id')) {
                        tab = i;
                        p.addClass(tabClass);
                    } else {
                        p.removeClass(tabClass);
                    }
                });
            } else {
                // 临时存储
                sessionStorage.setItem(crabMenus, JSON.stringify(menus));
                // 创建顶部导航
                $.each(menus, function (i, m) {
                    var cls = '';
                    if (i == tab) {
                        cls = ' ' + tabClass;
                    }
                    var uri = 'javascript:;', aClass = 'class="tabSwitch"';
                    if (m.uri) {
                        aClass = 'class="tabBlank"', uri = m.uri;
                    }
                    html += '<li class="layui-nav-item' + cls + '" lay-unselect>' +
                        '<a ' + aClass + ' data-id="' + m.id + '" href="' + uri + '" target="_blank">' + m.name + '</a>' +
                        '</li>';
                });
                $('#topMenu').append(html);
            }
            // 渲染左侧菜单
            var firstMenus = menus[tab].firstMenus;
            $('.layui-layout-admin .layui-side').load('components/side.html', function () {
                laytpl(sideNav.innerHTML).render(firstMenus, function (html) {
                    $('#sideNav').after(html);
                });
                element.render('nav');
                crab.activeNav(Q.lash);
            });

            var allMenus = [];
            for(var i=0;i<menus.length;i++){
                if(menus[i].firstMenus && menus[i].firstMenus.length >0){
                    allMenus = allMenus.concat(menus[i].firstMenus);
                }
            }
            console.log(allMenus);
            // 初始化路由
            index.initRouter(allMenus);
        },
        // 路由注册
        initRouter: function (menus) {
            Q.reg('jdc-djxx-form', function () {
                index.loadView('jdc-djxx-form', '/components/jdc/jdc-djxx-form.html', '登记表单');
            });
            index.regRouter(menus);
            Q.init({
                key: '',
                index: indexPage.menuId
            });
        },
        // 使用递归循环注册
        regRouter: function (menus) {
            $.each(menus, function (i, data) {
                if (data.uri && data.uri.indexOf('#') == 0) {
                    var menuId = data.uri.substring(1);
                    if(data.path === indexPage.page) indexPage.menuId = menuId;
                    Q.reg(menuId, function () {
                        index.loadView(menuId, 'components/' + data.path, data.name);
                    });
                }
                if (data.subMenus) {
                    index.regRouter(data.subMenus);
                }
            });
        },
        // 路由加载组件
        loadView: function (menuId, menuPath, menuName) {
            var contentDom = '.layui-layout-admin .layui-body';
            crab.showLoading('.layui-layout-admin .layui-body');
            var flag;  // 选项卡是否添加
            // 判断是否开启了选项卡功能
            if (config.pageTabs) {
                $('.layui-layout-admin .layui-body .layui-tab .layui-tab-title>li').each(function (index) {
                    if ($(this).attr('lay-id') === menuId) {
                        flag = true;
                    }
                });
                if (!flag) {
                    element.tabAdd('admin-pagetabs', {
                        title: menuName,
                        content: '<div id="' + menuId + '"></div>',
                        id: menuId
                    });
                }
                contentDom = '#' + menuId;
                element.tabChange('admin-pagetabs', menuId);
                crab.rollPage('auto');
                // 切换tab关闭表格内浮窗
                $('.layui-table-tips-c').trigger('click');
                // 解决切换tab滚动条时而消失的问题
                var $iframe = $('.layui-layout-admin .layui-body .layui-tab-content .layui-tab-item.layui-show .admin-iframe')[0];
                if ($iframe) {
                    $iframe.style.height = "99%";
                    $iframe.scrollWidth;
                    $iframe.style.height = "100%";
                }
                $(window).resize();
            }
            if (!flag || crab.isRefresh) {
                $(contentDom).load(menuPath, function () {
                    crab.isRefresh = false;
                    element.render('breadcrumb');
                    form.render('select');
                    crab.removeLoading('.layui-layout-admin .layui-body');
                });
            } else {
                crab.removeLoading('.layui-layout-admin .layui-body');
            }
            crab.activeNav(Q.lash);
            // 移动设备切换页面隐藏侧导航
            if (document.body.clientWidth <= 750) {
                crab.flexible(true);
            }
        },
        // 页面元素绑定事件监听
        bindEvent: function () {
            // 个人信息
            $('#myInfo').click(function () {
                crab.popupCenter({
                    title: '个人信息',
                    path: '/components/sys/user/user-info-form.html'
                });
            });
            // 退出登录
            $('#btnLogout').click(function () {
                layer.confirm('确定退出登录？', function (i) {
                    layer.close(i);
                    crab.clearUser();
                    location.replace('/sso/logout');
                });
            });
            // 修改密码
            $('#setPsw').click(function () {
                crab.popupRight('components/tpl/password.html');
            });
            // Tab 切换
            $('.tabSwitch').click(function () {
                index.initLeftNav(JSON.parse(sessionStorage.getItem(crabMenus)),
                    $(this).data("id"));
            });
            // 消息
            $('#btnMessage').click(function () {
                crab.popupRight('components/tpl/message.html');
            });
        },
        // 检查多标签功能是否开启
        checkPageTabs: function () {
            // 加载主页
            if (config.pageTabs) {
                $('.layui-layout-admin').addClass('open-tab');
                element.tabAdd('admin-pagetabs', {
                    title: '<i class="layui-icon layui-icon-home"></i>',
                    content: '<div id="console"></div>',
                    id: 'console'
                });
            } else {
                $('.layui-layout-admin').removeClass('open-tab');
            }
        },
        // 打开新页面
        openNewTab: function (param) {
            var url = param.url;
            var title = param.title;
            var menuId = param.menuId;
            if (!menuId) {
                menuId = url.replace(/[?:=&/]/g, '_');
            }

            Q.reg(menuId, function () {
                index.loadView(menuId, url, title);
            });

            Q.go(menuId);
        },
        // 关闭选项卡
        closeTab: function (menuId) {
            element.tabDelete('admin-pagetabs', menuId);
        }
    };

    // tab选项卡切换监听
    element.on('tab(admin-pagetabs)', function (data) {
        var layId = $(this).attr('lay-id');
        Q.go(layId);
    });

    // 初始化
    crab.init();

    // 移除loading动画
    setTimeout(function () {
        $('.page-loading').remove();
    }, 500);

    exports('index', index);
});
