//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	功能：前台使用的脚本库
//	@Language=javascript
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-



//页面自动刷新
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : setsecond		刷新的间隔时间
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function autorefresh(setsecond)
	{
		setTimeout("this.location.reload();",setsecond);
	}
//	end


//表格排序
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : obj		表格对象
//			sCount	单元格数
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function sortTable(obj,sCount) {
		var start=new Date;
		var theTable=new Array();
		var i;
		for(i=1;i<obj.rows.length;i++) {
		theTable[i-1]=new Array(obj.rows(i).cells(sCount).innerText.toLowerCase(),obj.rows(i));
		}
		theTable=theTable.sort();
		for(i=1;i<theTable.length;i++) {
		obj.lastChild.appendChild(theTable[i][1]);
		}
		window.status=(new Date-start)+' ms';
	}
//	end


//开新窗口
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : url			连接地址
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function openwin(url) {
		window.open(url,null,'width=240,height=240');
		
	}
//	end


//确认操作
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : str			提示文字
//			loc_href	跳转的页面
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function confirm_chk(str,loc_href)
	{
		if(confirm(str)) {
		void(location.href=loc_href);
		}
	}
//	end

//隐藏或显示表格多列
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : str		用于判断字符串
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function showhide_table(str){
		if(str == "new"){
			document.all.old.style.display = "none";
			document.all.newc.style.display = "block";
			document.all.newc2.style.display = "block";
			document.all.newc3.style.display = "block";
			document.all.newc4.style.display = "block";
			document.all.newc5.style.display = "block";
		}
		else if(str == "old"){
			document.all.old.style.display = "block";
			document.all.newc.style.display = "none";
			document.all.newc2.style.display = "none";
			document.all.newc3.style.display = "none";
			document.all.newc4.style.display = "none";
			document.all.newc5.style.display = "none";
		}
	}
//	end

//隐藏或显示表格单列
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : id		表格对象
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function showhide_single(id){
		var obj=eval("document.all."+id);
		if(obj.style.display == "none"){
			obj.style.display = "block";
		}
		else if(obj.style.display == "block"){
				obj.style.display = "none";		
		}
	}
//	end

//带查询条件翻页
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : no		第几页
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function jumpto(no,maxno){
		if(isNaN(no) || parseInt(no) != no || no<=0){
			alert("请输入1到"+ maxno +"之间的正整数！");
			document.form1.page.value="";
			return false;
		}  
		else if (no>maxno){
			alert("您只能输入1到"+ maxno +"之间的正整数！");
			document.form1.page.value="";
 			return false;
		} else {
			document.form1.page.value=no;
			document.form1.submit();
		}
	}
//	end
