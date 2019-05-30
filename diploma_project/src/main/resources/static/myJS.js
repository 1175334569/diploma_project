/*登录*/
function login() {
    var hide=document.getElementById("hide");
    var error_prompt=document.getElementById("error_prompt");
    var email=$("#email").val();
    var password=$("#password").val();
    if(email==""||password==""){
        error_prompt.innerText="帐号或密码不能为空！";
        hide.style.display="";
    }
    else {
        $.ajax({
            url:"findUser",
            data:{
                email:email,
                password:password
            },
            success:function (data) {
                if(data=="") {

                    error_prompt.innerText="帐号或密码错误！";
                    hide.style.display="";
                }
                else if(data=="1"){
                    window.location.href="/Admin";
                }
                else{
                    document.getElementById("login_top").style.display="none";
                    window.location.reload();
                }
            },
            error:function (data) {
                alert("失败");
            }
        });
    }
}

/*清除登录框信息*/
function clear_user() {
    document.getElementById("email").value="";
    document.getElementById("password").value="";
   document.getElementById("hide").style.display="none";
}

/*清除登录框信息*/
function clear_register() {
    document.getElementById("re_username").value="";
    document.getElementById("re_password").value="";
    document.getElementById("re_email").value="";
    document.getElementById("re_hide").style.display="none";
}
/*关闭登录框，显示注册框*/
function test() {
    clear_register();
    $("#login").modal("hide");
    $("#register").modal("show");
}
/* 冒泡框配置*/
$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});


/*js如何获取url的值*/
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if ( r != null ){
        return unescape(r[2]);
    }else{
        return null;
    }
}
//忘记密码操作
function forget_email() {
    var forget_email=document.getElementById("forget_email").value;
    var hide2=document.getElementById("hide2");
    var error_prompt=document.getElementById("error_prompt2");
    $.ajax({
        url:"forget_email_op",
        data:{
          email:forget_email
        },
        success:function (data) {
            if(data=="error1"){
               error_prompt.innerText="该邮箱还没注册！";
                error_prompt.color="red";
            }
            else if(data=="error2"){
                error_prompt.innerText="发送失败，请重新尝试！";
                error_prompt.color="red";
            }
            else {
                error_prompt.innerText="发送成功！请登录邮箱更改密码";
                error_prompt.color="green";
                setTimeout("window.location.href='/'",3500);
            }
            hide2.style.display="";
        }
    })

}
/*重置密码操作*/
function reset_password1(email,re_code) {
    var new_password=$("#new_password").val();

   $.ajax({
       url:"reset_password",
       data:{
           new_password:new_password,
           email:email,
           re_code:re_code
       },
       success:function (data) {
           if(data=="OK") {
               alert("密码修改成功！");
               setTimeout("window.location.href='/'",3000);
           }
           else alert("重置失败，请重新尝试");
           location.reload();
       }
   })
}
/*判断激活页面*/
function activation(email,code) {
    $.ajax({
        url:"activate",
        data:{
            email:email,
            code:code
        },
        success:function (data) {
            if(data=="OK"){
                document.getElementById("act").innerText="激活成功！";
            }
            else {
                document.getElementById("act").innerText="激活失败，链接已失效！";
            }
        }
    })
}
/*判断重置页面*/
function jude_reset_password(email,re_code) {
    $.ajax({
        url:"jude_reset_password",
        data:{
            email:email,
            re_code:re_code
        },
        success:function (data) {
            if(data=="OK"){
                document.getElementById("op_error").style.display="none";
                document.getElementById("op_success").style.display="";
            }else {
                document.getElementById("op_success").style.display="none";
                document.getElementById("op_error").style.display="";
                document.getElementById("op_error").innerText="重置失败，该链接已失效！";
                setTimeout("document.getElementById('jump_test').style.display=''",1500);
                setTimeout("window.location.href='/'",3000);
            }
        }
    })
}

function getCookie(c_name) {
    if(document.cookie.length > 0) {
        c_start = document.cookie.indexOf(c_name + "=");//获取字符串的起点
        if(c_start != -1) {
            c_start = c_start + c_name.length + 1;//获取值的起点
            c_end = document.cookie.indexOf(";", c_start);//获取结尾处
            if(c_end == -1) c_end = document.cookie.length;//如果是最后一个，结尾就是cookie字符串的结尾
            return decodeURI(document.cookie.substring(c_start, c_end));//截取字符串返回
        }
    }
    return "";
}



