package com.cashbookcloud.bill.service.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cashbookcloud.bill.api.dto.*;
import com.cashbookcloud.bill.api.service.BillService;
import com.cashbookcloud.bill.service.covert.BillCovert;
import com.cashbookcloud.bill.service.vo.BillVo;
import com.cashbookcloud.bill.service.vo.CountVo;
import com.cashbookcloud.bill.service.vo.PageVo;
import com.cashbookcloud.common.result.ResponseResult;
import com.cashbookcloud.common.utils.ExcelUtil;
import com.cashbookcloud.common.utils.POIUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@CrossOrigin
//@RequestMapping("/bill")
@RestController
@Api("账单管理服务")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * 获取所有账单
     * @param pageVo
     * @return
     */
    @ApiOperation(value = "获取所有账单",notes = "分页格式 ",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getall")
    public ResponseResult getAllPage(@RequestBody PageVo pageVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Page<BillDto> allPage = billService.findAllPage(pageVo.getPagenum(), pageVo.getPagesize(),pageVo.getQuery());
            List<BillVo> billDtos = new ArrayList<>();
            Page<BillVo> billVoPage = new Page<>();
            for (int i = 0; i < allPage.getRecords().size(); i++) {
                billDtos.add(BillCovert.INSTANCE.dto2vo(allPage.getRecords().get(i)));
            }
            billVoPage.setRecords(billDtos);
            billVoPage.setTotal(allPage.getTotal());
            billVoPage.setCountId(allPage.getCountId());
            billVoPage.setCurrent(allPage.getCurrent());
            billVoPage.setMaxLimit(allPage.getMaxLimit());
            billVoPage.setSize(allPage.getSize());
            billVoPage.setPages(allPage.getPages());
            result.Success("查询成功",billVoPage);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

//    @GetMapping("/getalltest")
//    public ResponseResult getAll(){
//        ResponseResult<Object> result = new ResponseResult<>();
//        try{
//            result.Success("查询成功");
//        }catch (Exception e){
//            e.printStackTrace();
//            result.FAIL_QUERY("查询失败");
//        }
//        return result;
//    }

    /**
     * 添加账单
     * @param billVo
     * @return
     */
    @ApiOperation(value = "添加账单",httpMethod = "POST",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BillVo.class,required = true,value = "账单Vo")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody BillVo billVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = BillCovert.INSTANCE.vo2dto(billVo);
            BillDto billDto1 = billService.addBill(billDto);
            BillVo billVo1 = BillCovert.INSTANCE.dto2vo(billDto1);
            result.Success("添加成功",billVo1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_ADD();
        }
        return result;
    }

    /**
     * 删除账单
     * @param id
     * @return
     */
    @ApiOperation(value = "删除账单",notes = "参数id ",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @DeleteMapping("/del")
    public ResponseResult delete(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            billService.deleteBillById(id);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 根据id查询账单
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询账单",notes = "参数id ",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/getById")
    public ResponseResult getById(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = billService.selectById(id);
//            BillVo billVo = BillCovert.INSTANCE.dto2vo(billDto);
            result.Success("查询成功",billDto);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY("查询失败");
        }
        return result;
    }

    /**
     * 更新账单
     * @param billVo
     * @return
     */
    @ApiOperation(value = "更新账单",httpMethod = "PUT",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = BillVo.class,required = true,value = "billVo")
    @PutMapping("/upd")
    public ResponseResult update(@RequestBody BillVo billVo){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            BillDto billDto = BillCovert.INSTANCE.vo2dto(billVo);
            BillDto billDto1 = billService.updateById(billDto);
            result.Success("更新成功",billDto1);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_UPDATE();
        }
        return result;
    }

    /**
     * 获取账单结余支出收入信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取账单结余支出收入信息",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "id")
    @GetMapping("/getkeeping")
    public ResponseResult getKeeping(Integer id){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            KeepingDto keeping = billService.getKeeping(id);
            result.Success("查询成功",keeping);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取根据账本id获取对应账单数量
     * @param billlistId
     * @return
     */
    @ApiOperation(value = "获取根据账本id获取对应账单数量",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "billlistId")
    @GetMapping("/getcount")
    public ResponseResult getCount(Integer billlistId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            Integer count = billService.getCount(billlistId);
            CountVo countVo = new CountVo();
            countVo.setCount(count);
            result.Success("ok",countVo);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取根据用户id和账本id获取对应账单
     * @param userId
     * @param billlistId
     * @return
     */
    @ApiOperation(value = "获取根据用户id和账本id获取对应账单",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId,billlistId")
    @GetMapping("/get")
    public ResponseResult Get(Integer userId,Integer billlistId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<BillDto> list = billService.findAllByUserIdAndBilllistId(userId, billlistId);
            result.Success("查询成功",list);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取用户每日支出图表
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户每日支出图表",notes = "与用户选择的账本无关",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
    @GetMapping("/getreportone")
    public ResponseResult getReportOne(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            CashReportDto reportOne = billService.getReportOne(userId);
            result.Success("查询成功",reportOne);
        }catch (Exception e){
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 获取用户分类支出图表
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户分类支出图表",notes = "与用户选择的账本无关",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userId")
    @GetMapping("/getreporttwo")
    public ResponseResult getReportTwo(Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try {
            List<CatReportDto> reportTwo = billService.getReportTwo(userId);
            result.Success("查询成功",reportTwo);
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_QUERY();
        }
        return result;
    }

    /**
     * 根据分类ID删除账单
     * @param catId
     * @return
     */
    @ApiOperation(value = "根据分类ID删除账单",notes = "根据分类ID删除账单",httpMethod = "DELETE",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "catId")
    @DeleteMapping("/delByCatId")
    public ResponseResult delByCatId(Integer catId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            billService.delByCatId(catId);
            result.Success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.FAIL_DELETE();
        }
        return result;
    }

    /**
     * 导出账单（测试ing）
     * @param request
     * @param response
     * @param userid
     * @param billlistid
     * @throws Exception
     */
    @ApiOperation(value = "导出账单（测试ing）",notes = "导出账单（测试ing）",httpMethod = "GET",response = ResponseResult.class)
    @ApiImplicitParam(dataTypeClass = Integer.class,required = true,value = "userid,billlistid")
    @PreAuthorize("hasAuthority('putbilllist')")
    @GetMapping("/getreportthree/{userid}/{billlistid}")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       @PathVariable("userid") Integer userid,
                       @PathVariable("billlistid") Integer billlistid) throws Exception {
        //获取数据
//        List<PageData> list = reportService.bookList(page);
        List<BillDto> list = billService.getReportThree(userid, billlistid);

        //excel标题
        String[] title = {"账单备注","分类","时间","支出/收入方式"};

        //excel文件名
        String fileName = "账单"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "账单";

        String[][] content = new String[10000][4];

        for (int i = 0; i < list.size(); i++) {
//            content[i] = new String[title.length];
            BillDto obj = list.get(i);
            content[i][0] = obj.getBillDescribe();
            content[i][1] = obj.getCatName();
            content[i][2] = obj.getBillDate();
            content[i][3] = obj.getBillMode();
        }

          //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            try {
//                System.out.println("ok!");
                this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * 发送响应流方法
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获取登录用户的信息
     * @return
     */
    @ApiOperation(value = "获取登录用户的信息",notes = "获取登录用户的信息",httpMethod = "GET",response = ResponseResult.class)
    @GetMapping("/getdetail")
    public ResponseResult getDetail(){
        ResponseResult<Object> result = new ResponseResult<>();
        String principal = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        result.Success("ok!",principal);
        return result;
    }

    /**
     * excel文件上传 并解析文件内容保存到数据库
     * @param excelFile
     * @return
     */
    @PostMapping("/upload/{billlistId}/{userId}")
    public ResponseResult upload(@RequestParam("excelFile") MultipartFile excelFile,@PathVariable("billlistId") Integer billlistId,@PathVariable("userId")Integer userId){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            List<String[]> list = POIUtils.readExcel(excelFile);
            for (String[] strings : list) {
                String billName = strings[0];
                String catName = strings[1];
                String billDate = strings[2];
                String billDescribe = strings[3];
                String billMode = strings[4];
                Double billPrice = Double.parseDouble(strings[5]);
                if(billDate == null){
                    continue;
                }
//                根据catname查询cat
                // TODO: 2022/6/22
                CatDto bycatName = billService.findBycatName(catName);
//                判断cat是否存在
                if(bycatName == null){
                    continue;
                }
//                若存在，cat与收入支出是否匹配
                if (bycatName.getCatDesc().equals(billDescribe)){
//                    说明匹配
                    BillDto billDto = new BillDto();
                    billDto.setCatId(bycatName.getId());
                    billDto.setBilllistId(billlistId);
                    billDto.setBillPrice(billPrice);
                    billDto.setBillMode(billMode);
                    billDto.setBillDate(billDate);
                    billDto.setBillName(billName);
                    billDto.setUserId(userId);
                    billDto.setBillDescribe(billDescribe);
                    billService.addBill(billDto);
                }else {
                    continue;
                }
            }
            result.Success("导入成功");
        } catch (IOException e) {
            e.printStackTrace();
            result.FAIL_ADD("导入账单失败");
        }
        return result;
    }

}
