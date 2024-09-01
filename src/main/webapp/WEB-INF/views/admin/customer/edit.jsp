<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/23/2024
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang Chủ</a>
                </li>
                <li class="active"><a href="/admin/customer-list">Quản lí khách hàng</a></li>
                <li class="active">
                    <i class="ace-icon"></i>Thêm khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thêm khách hàng

                </h1>


            </div><!-- /.page-header -->

            <!--Begin form-->
            <div class="row " style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="ModelCustomerDTO"  id="form-edit" method="GET" >
                    <form class="form-horizontal" role="form" style="margin-bottom: 40px;">
                        <form:input type="hidden" id="customerID" path="id"/>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Tên khách hàng</label>
                                <div class="col-sm-9 ">
                                    <form:input  type="text" class="form-control" id="name" path="name"/>
                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Số điện thoại</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" class="form-control" id="customerPhone" path="customerPhone"/>
                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Email</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="email" path="email" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Tên công ty</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="companyName" path="companyName"  class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Nhu cầu</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="demand" path="demand" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Tình trạng</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="status" path="status"
                                                class="form-control"/>
                                </div>

                            </div>

                        </div>



                        <div class="col-xs-12">
                            <div class=" row">
                                <div class="col-sm-9 pull-right">
                                    <c:choose>
                                        <c:when test="${ModelCustomerDTO.id == null}">
                                            <button class="btn btn-primary" id="btnAddBuilding" onclick="btnCreate()">Thêm khách hàng</button>
                                            <button class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                        </c:when>
                                        <c:when test="${ModelCustomerDTO.id != null}">
                                            <button class="btn btn-info" id="btnAddBuilding" onclick="btnUpdate()">Cập nhật khách hàng</button>
                                            <button class="btn btn-info" id="btnCancel">Hủy thao tác</button>
                                        </c:when>

                                    </c:choose>
                                </div>
                            </div>
                        </div>

                        <form:input type="hidden" path="id" />


                    </form>
                </form:form>

            </div>

            <c:forEach var = "item" items="${AssignmentCustomerType}">
                <div class="col-xs-12">
                    <h3 class="header smaller lighter blue">${item.value}</h3>
                    <button id="add${item.key}" class="btn btn-lg btn-primary">
                        <i class="orange ace-icon fa fa-location-arrow bigger-25">Add</i>
                    </button>
                    <c:if test="${item.key == 'CSKH'}">
                        <c:forEach var="ListCSKH" items="${ListCSKHs}">
                            <div class="col-xs-12">
                                <table id="staffList" class="table table-striped table-bordered table-hover"
                                       style="font-family: 'Times New Roman', Times, serif;">
                                    <thead>
                                    <tr>
                                        <th >
                                            Ngày tạo
                                        </th>
                                        <th>Người tạo</th>
                                        <th>Ngày sửa</th>
                                        <th>Người sửa</th>
                                        <th>Chi tiết giao dịch</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>${ListCSKH.createdDate}</td>
                                        <td>${ListCSKH.createdBy}</td>
                                        <td>${ListCSKH.modifiedDate}</td>
                                        <td>${ListCSKH.modifiedBy}</td>
                                        <td>${ListCSKH.note}</td>

                                        <td>

                                            <div class="hidden-sm hidden-xs btn-group">

                                                <button onclick="updateCSKH(${ListCSKH.id})" title="Chỉnh sửa nội dung"
                                                        class="btn btn-xs btn-success">

                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>

                                                </button>
                                                <security:authorize access="hasRole('MANAGER')" >
                                                    <button title="Xóa nội dung" onclick="btnDeleteOnly(${ListCSKH.id})" class="btn btn-xs btn-danger">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </button>
                                                </security:authorize>



                                            </div>



                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </c:forEach>

                    </c:if>
                    <c:if test="${item.key == 'DDX'}">
                        <c:forEach var="ListDDX" items="${ListDDXs}">
                            <div class="col-xs-12">
                                <table id="staffList" class="table table-striped table-bordered table-hover"
                                       style="font-family: 'Times New Roman', Times, serif;">
                                    <thead>
                                    <tr>
                                        <th >
                                            Ngày tạo
                                        </th>
                                        <th>Người tạo</th>
                                        <th>Ngày sửa</th>
                                        <th>Người sửa</th>
                                        <th>Chi tiết giao dịch</th>
                                        <th>Thao tác</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                        <td>${ListDDX.createdDate}</td>
                                        <td>${ListDDX.createdBy}</td>
                                        <td>${ListDDX.modifiedDate}</td>
                                        <td>${ListDDX.modifiedBy}</td>
                                        <td>${ListDDX.note}</td>
                                        <td>

                                            <div class="hidden-sm hidden-xs btn-group">

                                                <button onclick="updateDDX(${ListDDX.id})" title="Chỉnh sửa nội dung"
                                                        class="btn btn-xs btn-success">

                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>

                                                </button>
                                                <security:authorize access="hasRole('MANAGER')" >
                                                    <button title="Xóa giao dịch" onclick="btnDeleteOnly(${ListDDX.id})" class="btn btn-xs btn-danger">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </button>
                                                </security:authorize>




                                            </div>



                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </c:forEach>

                    </c:if>
                </div>
            </c:forEach>



            <!--End form-->
            <!--Table widget-->

            <!--Table widget-->
            <!-- /.main-content -->



            <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
            </a>
        </div><!-- /.main-container -->
    </div>
</div>
<div class="modal fade" id="assignmentBuildingModel" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">

                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                    <div class="col-xs-12 col-sm-9">
                       <span class="block input-icon input-icon-right">

                           <input type="text" name="transactionDetail"  id="transactionDetail" class="width-100">
                       </span>
                    </div>

                </div>
                <input type="hidden" id="code" name="code" value="" />
                <input type="hidden" id="customerTransID" name="customerTransID" value="" />
                <input type="hidden" id="idTransaction" name="idTransaction" value="" />

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="loadAddNote()">Thêm giao dịch</button>
                <button type="button" class="btn btn-default" onclick="cancelAddNote()">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    function btnDeleteOnly(id){
        $.ajax({
            type : "DELETE",
            url : "http://localhost:8081/api/customer/transaction-"+id,
            // data : JSON.stringify(data),
            // contentType : "application/JSON",
            // dataType : "JSON",
            success(e){


            },
            error(e){
                console.log("error")
            }
        })

    }
    function updateCSKH(id){
        var data = {};
        var code = "CSKH";
        var customerTransID = document.getElementById('customerID').value;
        $('#idTransaction').val(id);
        $('#code').val(code);
        $('#customerTransID').val(customerTransID);
        data["code"] = code;
        data["customerTransID"] = customerTransID;

        $('#assignmentBuildingModel').modal();
    }

    function updateDDX(id){
        var data = {};
        var code = "DDX";
        var customerTransID = document.getElementById('customerID').value;
        $('#idTransaction').val(id);
        $('#code').val(code);
        $('#customerTransID').val(customerTransID);
        data["code"] = code;
        data["customerTransID"] = customerTransID;

        $('#assignmentBuildingModel').modal();
    }

    $('#addCSKH').click(function (e){
        e.preventDefault();

        var data = {};
        var code = "CSKH";
        var customerTransID = document.getElementById('customerID').value;
        $('#code').val(code);
        $('#customerTransID').val(customerTransID);
        data["code"] = code;
        data["customerTransID"] = customerTransID;

        $('#assignmentBuildingModel').modal();

    })
    $('#addDDX').click(function (e){
        e.preventDefault();

        var data = {};
        var code = "DDX";
        var customerTransID = document.getElementById('customerID').value;
        $('#code').val(code);
        $('#customerTransID').val(customerTransID);
        data["code"] = code;
        data["customerTransID"] = customerTransID;

        $('#assignmentBuildingModel').modal();

    })

    function loadAddNote (){

        var note = document.getElementById('transactionDetail').value;
        var customerTransID=$('#customerTransID').val()
        var idTransaction =document.getElementById('idTransaction').value;
        var data = {
            idTransaction :idTransaction,
            code: $('#code').val(),
            customerTransID: customerTransID,
            note: note
        };

        window.location.href = "/admin/customer-edit-"+customerTransID;
        $.ajax({
            type : "PUT",
            url : "http://localhost:8081/api/customer/transaction",
            data : JSON.stringify(data),
            contentType : "application/JSON",
            dataType : "JSON",
            success(e){


            },
            error(e){
                console.log("error")
            }
        })

    }
    function  cancelAddNote (){
        var customerTransID=$('#customerTransID').val()
        window.location.href = "/admin/customer-edit-"+customerTransID;
    }

    $('#btnCancel').click(function (e){
        e.preventDefault();
        window.location.href ="/admin/customer-list";

    })

    function btnCreate (){
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData , function (index ,item){
            data[ "" + item.name + ""] = item.value ;
        })

        $.ajax({
            type : "PUT",
            url : "http://localhost:8081/api/customer/create",
            data : JSON.stringify(data),
            contentType : "application/JSON",
            dataType : "JSON",
            success(e){
                window.location.href ="/admin/customer-list";
            },
            error(e){
                console.log("error")
            }
        })

    }
    function btnUpdate(){
        e
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData , function (index ,item){
            data[ "" + item.name + ""] = item.value ;
        })
        window.location.href ="/admin/customer-list";
        $.ajax({
            type : "PUT",
            url : "http://localhost:8081/api/customer/update",
            data : JSON.stringify(data),
            contentType : "application/JSON",
            dataType : "JSON",
            success(e){
                console.log("success")
            },
            error(e){
                console.log("error")
            }
        })



    }

</script>
</body>
</html>
