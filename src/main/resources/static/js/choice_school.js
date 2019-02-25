//选择省份
$('.province').change(function(){
    var val = $(this).val();
    $.ajax({
        url:"/apps/dispensing/disschools?province=" + val,
        type:"post",
        dataType:"json",    //此处注明返回类型为json格式
        success:function(data){
            $(".school").children().remove();
            $(".school").append("<option value='0'>选择报考院校</option>");
            $.each(data, function(index,item){
                 $(".school").append(
                    "<option value='" + item.schoolCode + "'>"+item.schoolName+"</option>"
                 );
            });
        }
    });
});


//选择学校
$('.school').change(function(){
    var pro = $('.province option:selected');
    var school = $('.school option:selected');

    if(school.val() != "0")
    {
        $('section .hide-box').css('display','block');
        $('section .hide-box p span').html(school.text());
    }
     if(school.val() == "0")
    {
        $('section .hide-box').css('display','none');
    }
    else
    {
        $(".start-btn a").attr('href','/apps/dispensing/dismsg?schoolCode='+school.val()+'&schoolName='+school.text());
    }

});








