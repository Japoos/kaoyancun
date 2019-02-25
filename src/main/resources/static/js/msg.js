$('.fine input').on('input',function(){
	var lt = 0;
	var len = $('.fine').find('input').length;
	for(var i=0;i<len;i++){
		var val = parseInt($('.fine').find('input').eq(i).val());
		if(isNaN(val)){
			val = 0;
		}
		lt += val;
		if(lt <= 500){
			$('section .item.fine .title .fr em').html(lt);
		}else{
			$('.fine').find('input').val('');
			$('section .item.fine .title .fr em').html('- - -');
			alert('总分不能超过500分，请重新输入');
		}
	}
})



$(".major").on('input',function(){
    $(".yxs").css('display','block');
    dataMid={};
    dataMid.majorName = $(".major").val();
    dataMid.schoolCode=parseInt($("#schoolCode").text());
    $.ajax({
        type: "post",
        contentType:"application/json;charset=utf-8",
        data:JSON.stringify(dataMid),
        url: "/apps/dispensing/dismsg/mj",
        success: function (data) {
            $(".yxs").children().remove();
            $.each(data, function(index,item){
                $(".yxs").append(
                    "<p onclick='javascript:yxsclick(this)'>("+"<span>"+item.facultyCode+"</span>"+")"+"<span>"+item.facultyName+"</span>"+"-(<span>"+item.majorCode+"</span>)<span>"+item.majorName+"</span></p>"
                );
            });

        }
    });


});

var majorCode="";
var facultyCode="";

function yxsclick(obj)
{
     facultyCode = obj.getElementsByTagName("span")[0].innerHTML;
     var facultyName = obj.getElementsByTagName("span")[1].innerHTML;
     majorCode = obj.getElementsByTagName("span")[2].innerHTML;
     var majorName = obj.getElementsByTagName("span")[3].innerHTML;

    $("#faculty").val("("+facultyCode+")"+facultyName);
    $("#major").val("("+majorCode+")"+majorName);
    $(".yxs").css('display','none');

}

//调剂志愿（省份选择1）
$('#pro1').change(function(){
    var pro = $('#pro1 option:selected');
    if(pro.val()!=0)
    {
        $("#sc1").children().remove();
        $("#sc1").append("<option value='0'>选择报考院校</option>");
        $.post("/apps/dispensing/getschoolbyprovince",{"province":pro.text().trim()},function(result){
             $.each(result, function(index,item){
                    $("#sc1").append(
                    "<option value='" + item.dispensingId + "'>"+item.schoolName+"</option>"
                     );
             });
        });

    }

});

//调剂志愿（省份选择2）
$('#pro2').change(function(){
    var pro = $('#pro2 option:selected');
    if(pro.val()!=0)
    {
        $("#sc2").children().remove();
        $("#sc2").append("<option value='0'>选择报考院校</option>");
        $.post("/apps/dispensing/getschoolbyprovince",{"province":pro.text().trim()},function(result2){
             $.each(result2, function(index2,item2){
                    $("#sc2").append(
                    "<option value='" + item2.dispensingId + "'>"+item2.schoolName+"</option>"
                     );
             });
        });

    }

});

//调剂志愿（省份选择3）
$('#pro3').change(function(){
    var pro = $('#pro3 option:selected');
    if(pro.val()!=0)
    {
        $("#sc3").children().remove();
        $("#sc3").append("<option value='0'>选择报考院校</option>");
        $.post("/apps/dispensing/getschoolbyprovince",{"province":pro.text().trim()},function(result3){
             $.each(result3, function(index3,item3){
                    $("#sc3").append(
                    "<option value='" + item3.dispensingId + "'>"+item3.schoolName+"</option>"
                     );
             });
        });

    }

});

var diesch1="";
var diesch2="";
var diesch3="";

//调剂志愿（院校选择1）
$('#sc1').change(function(){
    var sch = $('#sc1 option:selected');
    if(sch.val()!=0)
    {
        diesch1=sch.val();
    }
});

//调剂志愿（院校2）
$('#sc2').change(function(){
    var sch = $('#sc2 option:selected');
    if(sch.val()!=0)
    {
        diesch2=sch.val();
    }
});

//调剂志愿（院校3）
$('#sc3').change(function(){
    var sch = $('#sc3 option:selected');
    if(sch.val()!=0)
    {
        diesch3=sch.val();
    }
});

// 验证码
$('.yzm').click(function(){
	var tel_num = $('.phone').val();
	if(!(/^1[34578]\d{9}$/.test(tel_num))){ 
        alert("您的手机号码有误，请重填");  
        return false; 
    }else if(tel_num == ''){
    	alert("请输入您的手机号码");  
        return false; 
    }else{
    	$(this).attr('disabled',true);  //当点击后倒计时时候不能点击此按钮 
	    var time = 60;  //倒计时60秒 
	    var timer = setInterval(fun1, 1000);  //设置定时器 
	    function fun1() { 
	      time--; 
	      if(time>=0) { 
	        $('.yzm').html(time+'s'); 
	      }else{ 
	        $('.yzm').html("重新发送"); 
	        $('.yzm').attr('disabled',false);   //倒计时结束能够重新点击发送的按钮 
	        clearTimeout(timer);  //清除定时器 
	        time = 60;  //设置循环重新开始条件 
	      } 
	    }
         //验证手机验证码
	     $.post("/apps/verTel",{"tel":tel_num},function(result){

	        //提交信息

            $(".start-btn a").on('click',function(){
                var date = {};
                date.userName=$("#uName").val();
                date.userTel=$("#uTel").val();
                date.userQq =$("#uQq").val();

                //添加用户

                $.ajax({
                    type: "post",
                    contentType:"application/json;charset=utf-8",
                    data:JSON.stringify(date),
                    url: "/apps/dispensing/dismsg/us",
                    success: function (data) {
                        if(data!=0)
                        {
                            var  dateMId = {};
                                 dateMId.schoolCodes=$("#schoolCode").text();
                                 dateMId.facultyCode=facultyCode;
                                 dateMId.majorCode =majorCode;

                            //添加用户成功返回uID，院校代码、院系代码、专业代码调取专业表ID

                            $.ajax({
                                type: "post",
                                contentType:"application/json;charset=utf-8",
                                data:JSON.stringify(dateMId),
                                url: "/apps/dispensing/dismsg/mId",
                                success: function (dataMajorId) {

                                      var dataAc={};
                                      dataAc.userId = parseInt(data);
                                      dataAc.politicalAch = parseInt($("#politicalAch").val());
                                      dataAc.forLangAch = parseInt($("#forLangAch").val());
                                      dataAc.matOrMajorOneAch = parseInt($("#matOrMajorOneAch").val());
                                      dataAc.majorSecAch = parseInt($("#majorSecAch").val());
                                      dataAc.majorId = parseInt(dataMajorId);

                                    //根据用户id、专业表ID添加成绩

                                    $.ajax({
                                        type: "post",
                                        contentType:"application/json;charset=utf-8",
                                        data:JSON.stringify(dataAc),
                                        url: "/apps/dispensing/dismsg/ac",
                                        success: function (dataAcbool) {

                                               //添加成绩后，添加可调剂院校

                                               var insDisUserData={};
                                               insDisUserData.userId =parseInt(data);
                                               dispenSingSchool1 =diesch1;
                                               dispenSingSchool2 =diesch2;
                                               dispenSingSchool3 = diesch3;
                                               $.ajax({
                                                   type: "post",
                                                   contentType:"application/json;charset=utf-8",
                                                   data:JSON.stringify(insDisUserData),
                                                   url: "/apps//dispensing/insDisUser",
                                                   success: function (insDisUserDatabool) {
                                                    console.log(insDisUserDatabool);
                                                   }
                                              });
                                        }
                                    });
                                    window.location.href="/apps/dispensing/disoK";

                                }
                            });
                        }
                    }
                });

            });

	     });

    }
})

