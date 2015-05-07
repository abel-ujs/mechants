var attaIdx = 0;
var attaIdxBig = 0;
    var IsIE;

    function fInitMSIE()
    {
	    if (navigator.userAgent.indexOf("MSIE") != -1)
	    {
		    IsIE = true;
	    }
	    else
	    {
		    IsIE = false;
	    }
    }
    fInitMSIE();
//search,全局变量
//-----------------------------------------------------------------------------------------------------------
    function IsIEBrowser() 
    {
	    if (navigator.userAgent.indexOf("MSIE") != -1)
	    {
		    return true;
		}
		else
		{
		    return false;
		}
    }

    // 增加附件函数 ()，增加到 idfilespan,基数为 attaIdx 。
    function add() 
    {
	    addfile("idfilespan",attaIdx);
	    attaIdx++;
	    return false;
    }

//----------------------------------------addfile(spanId,index)----------------------------------------------
    function addfile(spanId,index)
    {
//        debugger;
	    var strIndex = "" + index;
	    var fileId = "attachfile"+ strIndex;
	    var brId = "idAttachBr" + strIndex;
	    addInputFile(spanId,fileId);
	    adddel(spanId,index);
	    addPrev(spanId,index);    	
	    addbr(spanId,brId);	      
	    return;
    }
//-------------------------------------------sub function----------------------------------------------------
    function addInputFile(spanId,fileId)
    {   
          var span = document.getElementById(spanId);
          
          if ( span !=null )
          {
                if ( !IsIE )
                {
		            var fileObj = document.createElement("input");
		            if ( fileObj != null )
		            {
			            fileObj.type="file";
			            fileObj.name = fileId;
			            fileObj.id = fileId;
			            fileObj.size="80;";						      
			            var className = "inputtxt";
			            fileObj.setAttribute("class",className,0);
			            span.appendChild(fileObj);
		            }//if fileObj
	            }// !IsIE
	            if ( IsIE )
	            {
	            
		            var fileTag = "<input type='file' class='inputtxt' contenteditable='false' id ='" + fileId + "' name='uploadfile' size=40>";
		            var fileObj = document.createElement(fileTag); 
		            span.appendChild(fileObj);
		            
//		            var inputfileid = document.getElementById(fileId);
//		            inputfileid.click();
	            }//IsIE if
    			
          }//if span
    }
    


    function addbr(spanId,brId)
    {
	      var span = document.getElementById(spanId);
	      if ( span !=null )
	      {
              var brObj = document.createElement("br");
              if ( brObj !=null )
              {
                brObj.name = brId;
                brObj.id = brId;
                span.appendChild(brObj);
              }//if
          }//if
	     return;
    }

    function adddel(spanId,index)
    {
          var strIndex = "" + index;
	      var delId = "idAttachOper" + strIndex;
	      var span = document.getElementById(spanId);
	      if ( span != null )
	      {
			    var oTextNode = document.createElement("SPAN");
			    oTextNode.style.width = "5px";
			    span.appendChild(oTextNode);
		        if ( IsIE )
		        {
		            //class='ButtonDel' onmouseout=\"this.className='ButtonDel'\" onmouseover=\"this.className='ButtonDel'\"
	                var tag = "<input type='button'  id='" + delId + "' class='inputtxt' contenteditable='false' style='height:20px'  onclick=delfile('" + spanId + "',"+strIndex+")></input>";
			        var delObj = document.createElement(tag);
			        if ( delObj != null )
			        {
				        span.appendChild(delObj);
				        
				        
			        }//if
			    }// Is IE
    			
	            if ( !IsIE )
	            {
				    var delObj = document.createElement("input");
				    if ( delObj != null )
				    {
					    delObj.name = delId;
					    delObj.id = delId;
					    delObj.type = "button";
					    var clickEvent = "return delfile('" + spanId + "',"+strIndex+");";
					    delObj.setAttribute("onclick",clickEvent);  
					    span.appendChild(delObj);
				    }//if
			    }// !IsIE if
			    if( delObj != null) delObj.value = "删除";
		   }//main if
		    return;
    }
    

    function addPrev(spanId,index)
    {
          var strIndex = "" + index;
	      var prevId = "idAttachPrev" + strIndex;
	      var span = document.getElementById(spanId);
	      if ( span != null )
	      {			   
		        if ( IsIE )
		        {
		        // class='ButtonSearch' onmouseout=\"this.className='ButtonSearch'\" onmouseover=\"this.className='ButtonSearch'\"
	            var tag = "<input type='hidden' class='inputtxt' style='height:20px'  contenteditable='false' id='" + prevId + "' onclick=Prevfile('" + spanId + "',"+strIndex+")></input>";
			    var delObj = document.createElement(tag);
			    if ( delObj != null )
			    {
				    span.appendChild(delObj);
			    }//if

			    }// Is IE
    			
	            if ( !IsIE )
	            {
				    var delObj = document.createElement("input");
				    if ( delObj != null )
				    {
					    delObj.name = prevId;
					    delObj.id = prevId;
					    delObj.type = "button";
					    var clickEvent = "return Prevfile('" + spanId + "',"+strIndex+");";
					    delObj.setAttribute("onclick",clickEvent);  
					    span.appendChild(delObj);
				    }//if
			    }// !IsIE if
			    if( delObj != null){
			    	delObj.value = "预览";
			    	
			    }
		   }//main if
		   return;
    }
//---------------------------------------------delete input file-----------------------------------------------
    function delfile(spanId,index)
    {
	       var strIndex = "" + index;
	       var fileId = "attachfile"+ strIndex;
	       var brId = "idAttachBr" + strIndex;
	       var delId = "idAttachOper" + strIndex;
	       var preId = "idAttachPrev" + strIndex;	     
           var span = document.getElementById(spanId);	      
	       if ( span == null ) 
	            return false;
	       var fileObj = document.getElementById(fileId);
	       if ( fileObj == null )
	            return false;
	       var brObj = document.getElementById(brId);
	       if ( brObj ==null ) 
	            return false;    	   
	       var preObj = document.getElementById(preId);
		   if ( preObj ==null ) 
		        return false;    		
	       var delObj = document.getElementById(delId );	      
	       if ( delObj == null ) 
	            return false;          
	       var temp= document.createElement("SPAN");	       
	        span.replaceChild(temp,fileObj);
		    span.replaceChild(temp,brObj);
		    span.replaceChild(temp,preObj);		
		    span.removeChild(delObj.previousSibling);
		    var attach = document.getElementById(spanId);
		    if(span.getElementsByTagName("INPUT").length == 1) 
		        attach.childNodes[0].nodeValue='添加附件';
		   		    span.replaceChild(temp,delObj);   		
		    return false;
    }

    function Prevfile(spanId,strIndex)
    {
	    var fileId = "attachfile"+ strIndex;
	    var fileObj = document.getElementById(fileId);
	    var strLen = fileObj.value.length;
	    if(strLen==0) return;
    	
	    switch(fileObj.value.substr(strLen-4).toLowerCase())
	    {
		    case ".doc":
			    var wrd=new ActiveXObject("Word.Application")
			    wrd.visible=true
			    wrd.Documents.Open(fileObj.value)
			    break;
		    case ".xls":
			    xlApp = new ActiveXObject("Excel.Application");
			    xlApp.Visible = true;
			    //xlApp.Workbooks.Add();
			    xlApp.Workbooks.Open(fileObj.value);
			    break;
		    case ".txt":
			    new ActiveXObject("WScript.Shell").Run("notepad.exe " + fileObj.value)
			    break;
		    case ".pdf":
			    new ActiveXObject("WScript.Shell").Run("AcroRd32.exe " + fileObj.value)
			    break;
		    //case ".ceb":
			//    new ActiveXObject("WScript.Shell").Run("Reader.exe " + fileObj.value)
			//    break;
			case ".gif":
			case ".bmp":
			case ".png":
			case ".jpg":			     
			     new ActiveXObject("WScript.Shell").Run("iexplore.exe " + fileObj.value)	
			     break;	    
		    default:
			    alert("目前只支持Word、Excel、Txt、Pdf、Gif、BMP、Jpg、Png格式文件的预览功能！");
			    return;
	    }
    }
    function chksize(maxsize)
    {
	    var fso = new ActiveXObject('Scripting.FileSystemObject');
	    var spanId="idfilespan";
	    var span = document.getElementById(spanId);
	    var inputFile;
	    var File;
	    if(span.getElementsByTagName("INPUT").length==1)
		    return true;
	    else
	    {    		
		    if(span.getElementsByTagName("INPUT").length>1)
		    {    			
			    for(var i=0;i<span.getElementsByTagName("INPUT").length;i++)
			    {
				    inputFile = span.getElementsByTagName("INPUT")[i];					
				    if(inputFile.id.indexOf("attachfile") == -1) continue;
				    if(!fso.FileExists(inputFile.value)) continue;
				    File = fso.GetFile(inputFile.value);
				    if(File.Size>maxsize)
				    {
					    var seq = i / 2 + 1
					    alert ("抱歉！您选择的第" + seq + "个文件超过了指定大小，不能发送！");
					    return false;
				    }
			    }
		    }
	    }
	    return true;
    }

    function chkTotalsize(maxsize,oldtotalsize)
    {
	    var fso = new ActiveXObject('Scripting.FileSystemObject');
	    var spanId="idfilespan";
	    var span = document.getElementById(spanId);
	    var inputFile;
	    var File;
	    if(span.getElementsByTagName("INPUT").length==1)
		    return true;
	    else
	    {
		    if(span.getElementsByTagName("INPUT").length>1)
		    {
			    var totalFileLength=parseInt(oldtotalsize);
			    for(var i=0;i<span.getElementsByTagName("INPUT").length;i++)
			    {
				    inputFile = span.getElementsByTagName("INPUT")[i];
				    if(inputFile.id.indexOf("attachfile") == -1) continue;
				    if(!fso.FileExists(inputFile.value)) continue;
				    File = fso.GetFile(inputFile.value);
				    totalFileLength+=parseInt(File.Size);
			    }
			    if(totalFileLength>maxsize&&maxsize!=0)
			    {
			        alert ("抱歉！你的邮箱空间超过了使用限制，请删除不用的邮件或联系管理员");
			        return false;
			    }
		    }
	    }
	    return true;
    }
    
    
//---------------------------------------------大文件上传相关js代码 崔爱民 2008－1－7-----------------------------------
function OpenDialogArgs(url,args,width,height)
{   
    today=new Date();  
    if (url.indexOf("?")<0)
    {
        url=url+"?xxxx="+Math.random();
    }
    else
    {
        url=url+"&xxxx="+Math.random();
    }
    if (width==null)
        width=800;
    if (height==null)
        height=600;
    return window.showModalDialog(url,args,'dialogHeight:'+height+'px;dialogWidth:'+width+'px;edge:raised;center:Yes;help:No;resizable:Yes;status:no;scroll:yes;unadorned:yes;');
}



var arrBigFilePath = new Array(400);
var arrBigFileName= new Array(400);
var arrIndex= new Array(400);
var arrFileSize= new Array(400);
var arrFileContentType= new Array(400);

var BigFileIndex=1;
var objPathLst,objFileNameLst,objSizeLst,objContentTypeLst;

function CallBigFileUpload(BigUpFilePath, strPathLst,strFileNameLst,strSizeLst,strContentTypeLst)
{
    objPathLst=strPathLst;
    objFileNameLst=strFileNameLst;
    objSizeLst=strSizeLst;
    objContentTypeLst=strContentTypeLst;
    
    
    var spn = document.getElementById("idfilespanBig");
    var strFeatures="dialogWidth=450px;dialogHeight=60px;center=yes;middle=yes ;help=no;status=no;scroll=no";
    var url=BigUpFilePath;// '=Session["BigUpFilePath"] %><%';
		
    var arReturn=new Array();
    arReturn=OpenDialogArgs(BigUpFilePath,"",'450','60');		
    if (arReturn!=null && arReturn.length!=0)
    {
        arrBigFilePath[BigFileIndex]=arReturn[0];
        var arrS=arReturn[0].split('/');
        var FileName=arrS[arrS.length-1];
        arrBigFileName[BigFileIndex]=FileName;
        arrIndex[BigFileIndex]=BigFileIndex;
        arrFileSize[BigFileIndex]=arReturn[1];
        arrFileContentType[BigFileIndex]=arReturn[2];
        
        
        var fileObj = document.createElement("<lable id ='lblBigFileName" + BigFileIndex + "' name='lblBigFileName" + BigFileIndex + "' >"); 
        fileObj.innerText=FileName; 
        spn.appendChild(fileObj);

        var delTag = "<input type='button' class='BtnDel'id ='btndel" + BigFileIndex + "' onclick=\"DelBigFile('" + BigFileIndex + "')\"  name='btndel" + BigFileIndex + "' />";
        var delObj = document.createElement(delTag); 
        spn.appendChild(delObj);
        BigFileIndex++;
        
        SetValues();
    }
}

function DelBigFile(BigFileIndex)
{
    var span = document.getElementById("idfilespanBig");	      
    if ( span == null ) 
        return false;

    var fileObj = document.getElementById("lblBigFileName"+BigFileIndex);
    if ( fileObj == null )
        return false;

    var btnObj = document.getElementById("btndel"+BigFileIndex);
    if ( btnObj ==null ) 
        return false;   

    var temp= document.createElement("SPAN");	       
    span.replaceChild(temp,fileObj);
    span.replaceChild(temp,btnObj);
    
    arrBigFilePath[BigFileIndex]="";
    arrBigFileName[BigFileIndex]="";
    arrIndex[BigFileIndex]="";
    arrFileSize[BigFileIndex]="";
    arrFileContentType[BigFileIndex]="";

    SetValues();
}

function SetValues()
{
    var PathLst="",FileNameLst="",SizeLst="",ContentTypeLst="";
    for(i=1;i<=400 ; i ++)
    {
        if(arrBigFilePath[i]!=null && arrBigFilePath[i]!="")
        {
            PathLst+=arrBigFilePath[i]+"★";
            FileNameLst+=arrBigFileName[i]+"★";
            SizeLst+=arrFileSize[i]+"★";
            ContentTypeLst+=arrFileContentType[i]+"★";
        }
    }

    objPathLst.value=PathLst;
    objFileNameLst.value=FileNameLst;
    objSizeLst.value=SizeLst;
    objContentTypeLst.value=ContentTypeLst;
    
    //alert(objPathLst.value+"||"+objFileNameLst.value+"||"+objSizeLst.value+"||"+objContentTypeLst.value);
}

function ClearArray()
{
     arrBigFilePath = new Array(400);
     arrBigFileName = new Array(400);
     arrIndex = new Array(400);
     arrFileSize = new Array(400);
     arrFileContentType = new Array(400);
}
