//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	���ܣ�ǰ̨ʹ�õĽű���
//	@Language=javascript
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-



//ҳ���Զ�ˢ��
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : setsecond		ˢ�µļ��ʱ��
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function autorefresh(setsecond)
	{
		setTimeout("this.location.reload();",setsecond);
	}
//	end


//�������
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : obj		������
//			sCount	��Ԫ����
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


//���´���
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : url			���ӵ�ַ
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function openwin(url) {
		window.open(url,null,'width=240,height=240');
		
	}
//	end


//ȷ�ϲ���
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : str			��ʾ����
//			loc_href	��ת��ҳ��
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function confirm_chk(str,loc_href)
	{
		if(confirm(str)) {
		void(location.href=loc_href);
		}
	}
//	end

//���ػ���ʾ������
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : str		�����ж��ַ���
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

//���ػ���ʾ�����
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : id		������
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

//����ѯ������ҳ
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
//	input : no		�ڼ�ҳ
//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
	function jumpto(no,maxno){
		if(isNaN(no) || parseInt(no) != no || no<=0){
			alert("������1��"+ maxno +"֮�����������");
			document.form1.page.value="";
			return false;
		}  
		else if (no>maxno){
			alert("��ֻ������1��"+ maxno +"֮�����������");
			document.form1.page.value="";
 			return false;
		} else {
			document.form1.page.value=no;
			document.form1.submit();
		}
	}
//	end
