<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/23/2024
  Time: 3:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                <li class="active"> Quản lí khách hàng</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Danh sách khánh hàng

                </h1>
            </div><!-- /.page-header -->

            <!--widget-->
            <div class="row">
                <div class="widget-box ui-sortable-handle">
                    <div class="widget-header">
                        <h5 class="widget-title">Tìm kiếm</h5>

                        <div class="widget-toolbar">


                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>


                        </div>
                    </div>

                    <div class="widget-body" style="font-family: 'Times New Roman', Times, serif">
                        <div class="widget-main">
                            <form:form modelAttribute="ModelCustomerRequest" id="listForm" action="/admin/customer-list" method="GET">
                            <div class="row" >
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <div class="col-xs-4">
                                            <label class="name">
                                                Tên khách hàng
                                            </label>

                                            <form:input type="text" id="name" path="name"   class="col-xs-12 col-sm-6 form-control" />
                                        </div>
                                        <div class="col-xs-4">
                                            <label class="name">
                                                Số điện thoại
                                            </label>

                                            <form:input type="text" id="customerPhone"  path="customerPhone" class=" form-control" />
                                        </div>
                                        <div class="col-xs-4">
                                            <label class="name">
                                               Email
                                            </label>

                                            <form:input type="text" id="email"  path="email" class=" form-control" />
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-xs-12" style="padding-top: 5px;">

                                        <div class="col-xs-2">
                                            <security:authorize access="hasRole('MANAGER')" >
                                                <label class="name">
                                                    Chọn nhân viên phụ trách
                                                </label>

                                                <form:select class="form-control" id="staffId" path="staffId"
                                                             placeholder="--Chọn nhân viên-- ">
                                                    <form:option value="">--Chọn nhân viên-- </form:option>
                                                    <form:options items="${listStaffs}"/>



                                                </form:select>
                                            </security:authorize>


                                        </div>

                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-xs-12" style="padding-top: 5px;">
                                        <div class="col-xs-6">
                                            <button style="align-items: center;" type="button" id="btnSearchCustomer"
                                                    class="btn btn-success" fdprocessedid="jdaam">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="13" height="13"
                                                     fill="currentColor" class="bi bi-search"
                                                     viewBox="0 0 16 16">
                                                    <path
                                                            d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0">
                                                    </path>
                                                </svg>
                                                Tìm kiếm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form:form>

                    </div>


                </div><!-- /.page-content -->

                <div class="pull-right">
                    <security:authorize access="hasRole('MANAGER')" >
                        <a href="/admin/customer-edit">
                            <button class="btn btn-success" title="Thêm khách hàng">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0" />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                                </svg>
                            </button>
                        </a>

                        <button class="btn btn-info" onclick="btnDeleteAll()" title="xóa tòa nhà">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-building-dash" viewBox="0 0 16 16">
                                <path
                                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
                                <path
                                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
                                <path
                                        d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
                            </svg>
                        </button>
                    </security:authorize>


                </div>



            </div>
        </div>
        <!--/widget-->

        <!--Table widget-->
        <div class="row"
             style="padding-top: 40px; padding-bottom: 20px; font-family: 'Times New Roman', Times, serif; font-size: 14px;">
            <div class="col-xs-12">
                <table id="simple-table" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th>Tên khách hàng</th>
                        <th>Di động </th>

                        <th>Email</th>

                        <th>

                            Nhu cầu
                        </th>
                        <th>Người thêm</th>
                        <th>Ngày thêm</th>
                        <th>Người sửa đổi</th>
                        <th>Ngày sửa đổi</th>
                        <th>Tình trạng</th>
                        <th>Thao tác</th>


                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="ListCustomer" items="${ListCustomerResponse}">
                        <tr>

                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" value="${ListCustomer.id}" class="ace">
                                    <span class="lbl"></span>
                                </label>
                            </td>

                            <td>
                                    ${ListCustomer.name}
                            </td>
                            <td>${ListCustomer.customerPhone}</td>
                            <td >${ListCustomer.email}</td>
                            <td>${ListCustomer.demand}</td>

                            <td >
                                    ${ListCustomer.createdBy}
                            </td>
                            <td>${ListCustomer.createdDate}</td>
                            <td >
                                    ${ListCustomer.modifiedBy}
                            </td>
                            <td>${ListCustomer.modifiedDate}</td>
                            <td>${ListCustomer.status}</td>

                            <td>

                                <div class="hidden-sm hidden-xs btn-group">
                                    <security:authorize access="hasRole('MANAGER')" >
                                        <button onclick="assignmentBuilding(${ListCustomer.id})" title="Giao khách hàng"
                                                class="btn btn-xs btn-success">

                                            <i class="ace-icon glyphicon glyphicon-list"></i>

                                        </button>
                                    </security:authorize>


                                    <a href="/admin/customer-edit-${ListCustomer.id}"   title="Chỉnh sửa khách hàng" class="btn btn-xs btn-info">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access="hasRole('MANAGER')" >
                                        <button title="Xóa tòa nhà" onclick="btnDeleteOnly(${ListCustomer.id})" class="btn btn-xs btn-danger">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>




                                </div>


                                <div class="hidden-md hidden-lg">
                                    <div class="inline pos-rel">
                                        <button class="btn btn-minier btn-primary dropdown-toggle"
                                                data-toggle="dropdown" data-position="auto">
                                            <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                        </button>

                                        <ul
                                                class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a href="#" class="tooltip-info" data-rel="tooltip" title=""
                                                   data-original-title="View">
																<span class="blue">
																	<i
                                                                            class="ace-icon fa fa-search-plus bigger-120"></i>
																</span>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="tooltip-success" data-rel="tooltip"
                                                   title="" data-original-title="Edit">
																<span class="green">
																	<i
                                                                            class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																</span>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="tooltip-error" data-rel="tooltip"
                                                   title="" data-original-title="Delete">
																<span class="red">
																	<i class="ace-icon fa fa-trash-o bigger-120"></i>
																</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </td>




                        </tr>
                    </c:forEach>


                    </tbody>
                </table>


            </div>
            <!-- /.span -->
            <div class="col-xs-12">
                <c:if test="${totalPages == 0}">
                    <h4 class="center">không có dữ liệu</h4>
                </c:if>
                <c:if test="${totalPages != 0}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:if test="${currentPage == 1}">
                                <li class="disabled page-item">
                                    <span aria-hidden="true">&laquo;</span>
                                </li>
                            </c:if>
                            <c:if test="${currentPage != 1}">
                                <li class="page-item">
                                    <a
                                            href="/admin/customer-list?page=${currentPage - 1}"
                                            aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                <li class="page-item">
                                    <a class="${ currentPage eq (loop.index +1) ? 'active page-link ' : 'page-link'}"
                                       href="/admin/customer-list?page=${loop.index +1}">${loop.index +1}
                                    </a>
                                </li>
                            </c:forEach>
                            <c:if test="${currentPage != totalPages}">
                                <li class="page-item">
                                    <a
                                            href="/admin/customer-list?page=${currentPage + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${currentPage == totalPages}">
                                <li class="disabled page-item">
                                    <span aria-hidden="true">&raquo;</span>
                                </li>
                            </c:if>

                        </ul>
                    </nav>
                </c:if>

            </div>

        </div>
        <!--/Table widget-->

        <!-- /.main-content -->

        <!--footer-->

        <!--/footer-->

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->

</div>
<div class="modal fade" id="assignmentCustomModel" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">

                <table id="staffList" class="table table-striped table-bordered table-hover"
                       style="font-family: 'Times New Roman', Times, serif;">
                    <thead>
                    <tr>
                        <th class="center">
                            Chọn
                        </th>
                        <th class="center">Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>





                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="loadStaff()">Giao khách hàng</button>
                <button type="button" class="btn btn-default" id="btnCancel">Đóng</button>
            </div>
        </div>

    </div>
</div>

<script>
    $('#btnSearchCustomer').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    })

    function assignmentBuilding (customerId){


        $('#customerId').val(customerId);
        var data = {};
        data['customerId'] = customerId;
        $.ajax({
            type: "POST",
            url: "http://localhost:8081/api/customer/"+customerId+"/customer",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                var row = '';
                $.each( response.data , function (index ,item){
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.staffId + ' id="checkbox_' + item.staffId + '" class = "check-box-element"' + item.checked +'/></td>';
                    row += '<td class="text-center">'+item.fullName+'</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
                console.log("cussess");
            },
            error: function (respond) {
                console.log(respond)
            }
        });
        $('#assignmentCustomModel').modal();
    }

    function loadStaff (e) {

        var data = {};
        data['customerId'] = $('#customerId').val();
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        $.ajax({
            type: "PUT",
            url: "http://localhost:8081/api/customer/assignmentCustomer",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "JSON",
            success: function (response) {
                console.log("success")
            },
            error: function (response) {
                console.log("success")
            }
        })

        window.location.href ="/admin/customer-list";

    }
    $('#btnCancel').click(function (e){

        window.location.assign("/admin/customer-list");

    })

    function  btnDeleteOnly ( customerId ){

        DeleteCustomerId(customerId);
        window.location.href ="/admin/customer-list";
    }

    function btnDeleteAll(){


        var customerId = $('#simple-table').find("tbody input[type=checkbox]:checked").map(function (index,item) {
            return item.value;
        }).get();

        DeleteCustomerId(customerId)
        window.location.href ="/admin/customer-list";
    }

    function DeleteCustomerId (data){

        $.ajax({
            type : "DELETE",
            url : "http://localhost:8081/api/customer/"+data,
            // data : JSON.stringify(data),
            // contentType : "application/JSON",
            // dataType : "JSON",
            success(e){
                console.log("success")
            },
            error(e){
                console.log(("error"))
            }
        })

    }
</script>

</body>
</html>
