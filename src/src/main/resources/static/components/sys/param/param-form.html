<!-- 参数表单弹窗 -->
<form id="param-form" lay-filter="param-form" class="layui-form model-form">
    <input name="id" type="hidden"/>
    <div class="layui-form-item">
        <label class="layui-form-label">名称</label>
        <div class="layui-input-block">
            <input name="name" placeholder="请输入名称" type="text" class="layui-input" minlength="6" maxlength="100"
                   lay-verify="required" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">编码</label>
        <div class="layui-input-block">
            <input name="code" placeholder="请输入编码" type="text" class="layui-input" minlength="6" maxlength="100"
                   lay-verify="required" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">
            <input name="content" placeholder="请输入内容" type="text" class="layui-input" minlength="6" maxlength="100"
                   lay-verify="required" required/>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">系统参数</label>
        <div class="layui-input-block">
            <input type="checkbox" name="sys" value="1" lay-skin="switch" lay-text="是|否" lay-filter="switchSys">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入备注" class="layui-textarea" maxlength="255" lay-verify="required"
                      required></textarea>
        </div>
    </div>
    <div class="layui-form-item model-form-footer">
        <button class="layui-btn layui-btn-primary close" ew-event="closeDialog" type="button">取消</button>
        <button class="layui-btn" lay-filter="param-form-submit" lay-submit>保存</button>
    </div>
</form>
<script>
    layui.use(['layer', 'crab', 'form'], function () {
        var layer = layui.layer;
        var crab = layui.crab;
        var form = layui.form;
        var edit = false;

        form.render('checkbox');

        // 编辑的参数信息
        var param = crab.getFormData();
        if (param) {
            edit = true;
            // 回显参数数据
            form.val('param-form', param);
        }

        // 表单提交事件
        form.on('submit(param-form-submit)', function (data) {
            if (1 != data.field.sys) {
                data.field.sys = 0;
            }
            crab.request('/sys/param', data.field, function () {
                layer.msg(edit ? '修改成功' : '添加成功', {icon: 1});
                crab.finishPopupCenter();
            }, edit ? 'PUT' : 'POST');
            return false;
        });
    });
</script>