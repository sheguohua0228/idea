package com.aplus.lk.clothes.utils;
 
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aplus.lk.clothes.entity.ClothesOrder;

/**
 * 
* @TypeName: DownloadExcelUtils 
* @Description: TODO
* @author：Jingpeng 
* @date 2016年3月16日 上午10:15:44 
*
 */
public class DownloadExcelUtils {
	private final static Logger logger=LoggerFactory.getLogger(DownloadExcelUtils.class);
	/**定义输出cell常量*/
	private final static int CELLNUM = 100;
	
	static WritableFont wfTitle=null;// 定义格式 字体 下划线 斜体 粗体 颜色
	static WritableFont wfCon=null;// 定义格式 字体 下划线 斜体 粗体 颜色
	static WritableCellFormat wcfTitle =null;
	static WritableCellFormat wcfCon =null;
	static int titleRowHeight=700; 
	static int titleColWidth=25; 
	static int conRowHeight=400;
	static int titleLengthV=3;//标题列长度X设置列宽的参数
	
	private static void InitParam(){ 
		wfTitle = new WritableFont(WritableFont.TAHOMA, 11,
			     WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
			     jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
		wcfTitle = new WritableCellFormat(wfTitle); // 单元格定义 
		 
		 wfCon = new WritableFont(WritableFont.TAHOMA, 10,
			     WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
			     jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
		 wcfCon = new WritableCellFormat(wfCon); // 单元格定义 
		try {
			wcfTitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfTitle.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			wcfTitle.setBackground(jxl.format.Colour.GREY_25_PERCENT);
			
			wcfCon.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			wcfCon.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
			 
			
		} catch (WriteException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * 插入图片到EXCEL  添加 2013-01-14
	 *  
	 * @param picSheet sheet  
	 * @param cellRow 行数 
	 * @param cellCol 列数 
	 * @throws Exception 例外 
	 */  
	private static void addPictureToExcel(WritableSheet picSheet,ByteArrayOutputStream picStream, int cellRow, int cellCol)  
	    throws Exception {  
	    // 开始位置  
	    double picBeginCol = 0;  
	    double picBeginRow =1;  
	    // 图片时间的高度，宽度  
	    double picCellWidth = 0.0;  
	    double picCellHeight = 0.0;  
	    // 读入图片  
	    BufferedImage picImage = ImageIO.read(new ByteArrayInputStream(picStream.toByteArray()));
	    // 取得图片的像素高度，宽度  
	    int picWidth = picImage.getWidth();  
	    int picHeight = picImage.getHeight();  
	      
	    // 计算图片的实际宽度  
	    int picWidth_t = picWidth * 32;  //具体的实验值，原理不清楚。  
	    for (int x = 0; x < 1234; x++) {  
	        int bc = (int) Math.floor(picBeginCol + x);  
	        // 得到单元格的宽度  
	        int v = picSheet.getColumnView(bc).getSize();  
	        double offset0_t = 0.0;  
	        if (0 == x)  
	            offset0_t = (picBeginCol - bc) * v;  
	        if (0.0 + offset0_t + picWidth_t > v) {  
	            // 剩余宽度超过一个单元格的宽度  
	            double ratio_t = 1.0;  
	            if (0 == x) {  
	                ratio_t = (0.0 + v - offset0_t) / v;  
	            }  
	            picCellWidth += ratio_t;  
	            picWidth_t -= (int) (0.0 + v - offset0_t);  
	        } else { //剩余宽度不足一个单元格的宽度  
	            double ratio_r = 0.0;  
	            if (v != 0)  
	                ratio_r = (0.0 + picWidth_t) / v;  
	            picCellWidth += ratio_r;  
	            break;  
	        }  
	    }          
	    // 计算图片的实际高度  
	    int picHeight_t = picHeight * 15;  
	    for (int x = 0; x < 1234; x++) {  
	        int bc = (int) Math.floor(picBeginRow + x);  
	        // 得到单元格的高度  
	        int v = picSheet.getRowView(bc).getSize();  
	        double offset0_r = 0.0;  
	        if (0 == x)  
	            offset0_r = (picBeginRow - bc) * v;  
	        if (0.0 + offset0_r + picHeight_t > v) {  
	            // 剩余高度超过一个单元格的高度  
	            double ratio_q = 1.0;  
	            if (0 == x)  
	                ratio_q = (0.0 + v - offset0_r) / v;  
	            picCellHeight += ratio_q;  
	            picHeight_t -= (int) (0.0 + v - offset0_r);  
	        } else {//剩余高度不足一个单元格的高度  
	            double ratio_m = 0.0;  
	            if (v != 0)  
	                ratio_m = (0.0 + picHeight_t) / v;  
	            picCellHeight += ratio_m;  
	            break;  
	        }  
	    }  
	    //生成一个图片对象。  
	    WritableImage image = new WritableImage(picBeginCol, picBeginRow,  
	            picCellWidth, picCellHeight,picStream.toByteArray());  
	    // 把图片插入到sheet  
	    picSheet.addImage(image);  
	}  
	
	public static void main(String[] args) throws IOException {
		 
		String data="orderNumber:订单编号 ,orderStatus:订单状态,payStatus:支付状态,status:处理状态,price:订单价格,barCode:条形码";
		Collection<ClothesOrder> collData=new LinkedList<ClothesOrder>();
		ClothesOrder order=null;
		for(int i=0;i<10;i++){
			order=new ClothesOrder();
			order.setOrderNumber(i+"");
			order.setOrderStatus(i);
			order.setPayStatus(i);
			order.setStatus(i);
			order.setPrice(new BigDecimal(i));
			order.setBarCode(i+"");
			collData.add(order);
		}
		
		ByteArrayOutputStream stream = fullExcel(data, collData, ClothesOrder.class);
        FileOutputStream fos = new FileOutputStream("G://test.xls");
		byte[] byteArray = stream.toByteArray();
		 
		fos.write(byteArray);
		fos.close();
	}
	
	 
	/** 
	 * 
	 * @param data 定义excel结构的数据
	 * @param collData 要填充excel的数据
	 * @param clazz 要填充的对象
	 * @param picStream 要填充的图片流
	 * @param fromRow 从哪 一行填充图片
	 * @return excel输出流 
	 */
	public static ByteArrayOutputStream fullExcel(String data,Collection<?> collData,Class<?> clazz,ByteArrayOutputStream picStream,int fromRow){
		InitParam();
		/**控制数据填充在哪一行的变量*/
		int contentNum = fromRow;		
		/**定义输出sheet*/
		 int sheetNum = 1;
		 /**为防止null引发异常*/
		collData = collData == null?new ArrayList():collData;
		/**如果数据在一个sheet中显示不完，增加sheet数*/
		if(collData.size()>CELLNUM){
			sheetNum = collData.size()%CELLNUM == 0 ?collData.size()/CELLNUM:collData.size()/CELLNUM+1;
		}
		Iterator<?> it = collData.iterator();
		/**创建文件输出流*/
		ByteArrayOutputStream out = new ByteArrayOutputStream();		
		try{
			/**通过输出流创建输出对象*/
			WritableWorkbook book = Workbook.createWorkbook(out);
			 
		 
			for(int i=1;i<=sheetNum;i++){
				/**创建输出sheet*/
				WritableSheet sheet = book.createSheet("第"+i+"页",i);
				
				/**填充表头列*/
				Map<String, Integer> map = creatHeadLable(sheet, data,fromRow-1);		
				
				 
				addPictureToExcel(sheet,picStream,10,map.size());
				
				while(it.hasNext()){
					if(contentNum > CELLNUM){
						contentNum = 1;
						break;
					}
					Object obj = it.next();
					for(String name:map.keySet()){
						String fdName_up="get"+name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
						try{
							Method setter=clazz.getMethod(fdName_up, null);
							Object rt=setter.invoke(obj);
							Label label = new Label(map.get(name), contentNum,rt!=null?rt.toString():null,wcfCon);  
							sheet.addCell(label); 
							 
						}catch (Exception e) {
							logger.error("找不到"+fdName_up+"方法", e);
							continue;
						} 						
					}
					sheet.setRowView(contentNum,conRowHeight);
					contentNum++;
				}
			}
			book.write();  
            book.close(); 
		}catch (Exception e) {
			logger.error("导出excel出错",e);
		}
		return out;
	}
	
	
	/**
	 * 
	 * @param data 定义excel结构的数据
	 * @param collData 要填充excel的数据
	 * @param clazz 要填充的对象
	 * @return excel输出流
	 */
	public static ByteArrayOutputStream fullExcel(String data,Collection<?> collData,Class<?> clazz){
		InitParam();
		/**控制数据填充在哪一行的变量*/
		int contentNum = 1;		
		/**定义输出sheet*/
		 int sheetNum = 1;
		 /**为防止null引发异常*/
		collData = collData == null?new ArrayList():collData;
		/**如果数据在一个sheet中显示不完，增加sheet数*/
		if(collData.size()>CELLNUM){
			sheetNum = collData.size()%CELLNUM == 0 ?collData.size()/CELLNUM:collData.size()/CELLNUM+1;
		}
		Iterator<?> it = collData.iterator();
		/**创建文件输出流*/
		ByteArrayOutputStream out = new ByteArrayOutputStream();		
		try{
			/**通过输出流创建输出对象*/
			WritableWorkbook book = Workbook.createWorkbook(out);
			for(int i=1;i<=sheetNum;i++){
				/**创建输出sheet*/
				WritableSheet sheet = book.createSheet("第"+i+"页",i);
				/**填充表头列*/
				Map<String, Integer> map = creatHeadLable(sheet, data);								
				while(it.hasNext()){
					if(contentNum > CELLNUM){
						contentNum = 1;
						break;
					}
					Object obj = it.next();
					for(String name:map.keySet()){
						String fdName_up="get"+name.substring(0, 1).toUpperCase()+name.substring(1, name.length());
						try{
							Method setter=clazz.getMethod(fdName_up, null);
							Object rt=setter.invoke(obj);
							Label label = new Label(map.get(name), contentNum,rt!=null?rt.toString():null,wcfCon);  
							sheet.addCell(label); 
							
						}catch (Exception e) {
							logger.error("找不到"+fdName_up+"方法", e);
							continue;
						} 						
					}
					sheet.setRowView(contentNum,conRowHeight);
					contentNum++;
				}
			}
			book.write();  
            book.close(); 
		}catch (Exception e) {
			logger.error("导出excel出错",e);
		}
		return out;
	}
	/**  duanwei 添加 2013-01-14
	 * 解析data填充表头列并生成map
	 * @param sheet
	 * @param data
	 * @param fromRow 从第几行添加列头
	 * @return
	 * @throws Exception
	 */
	private static Map<String,Integer> creatHeadLable(WritableSheet sheet,String data,int fromRow) throws Exception{
		/**去除jqGrid自动返回的rn字段*/
		 
		data = data.substring(data.indexOf(",")+1);
		/**map<字段名,列>*/
		Map<String,Integer> map = new HashMap<String,Integer>();
		String cols[]=data.split(","); 
		
    	for(int i=0;i<cols.length;i++){
    		if(!StringUtils.isEmpty(cols[i])){
    			String[] ac=cols[i].split(":");
    			map.put(ac[0],i);
    			//添加第一行标题
    			Label label = new Label(i,fromRow, ac[1],wcfTitle);  
 	            sheet.addCell(label);  
 	            sheet.setColumnView(i,  ac[1].toString().length()*titleLengthV);//设置列宽
 	            if(i==0){
 	            	 sheet.setColumnView(i,  13);
 	            }
    		}else{
    			logger.error("前台数据源解析出错",data);
    			throw new Exception("前台数据源【"+data+"】解析出错");
    		}    		
    	}
    	sheet.setRowView(fromRow,titleRowHeight);//设置行高
    	return map;
	}
	/**
	 * 解析data填充表头列并生成map
	 * @param sheet
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static Map<String,Integer> creatHeadLable(WritableSheet sheet,String data) throws Exception{
		/**去除jqGrid自动返回的rn字段*/
		data = data.substring(data.indexOf(",")+1);
		/**map<字段名,列>*/
		Map<String,Integer> map = new HashMap<String,Integer>();
		String cols[]=data.split(",");
    	for(int i=0;i<cols.length;i++){
    		if(!StringUtils.isEmpty(cols[i])){
    			String[] ac=cols[i].split(":");
    			map.put(ac[0],i);
    			//添加第一行标题
    			Label label = new Label(i, 0, ac[1],wcfTitle);  
 	            sheet.addCell(label); 
 	            sheet.setColumnView(i,titleColWidth);//设置列宽
	             
    		}else{
    			logger.error("前台数据源解析出错",data);
    			throw new Exception("前台数据源【"+data+"】解析出错");
    		}    		
    	}
    	sheet.setRowView(0,titleRowHeight);//设置行高
    	return map;
	}
}
