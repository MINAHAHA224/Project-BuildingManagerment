<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/15/2024
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Thêm tòa nhà</title>

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
                <li class="active"> Quản lí tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Quản Lí Tòa Nhà

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
                            <form:form modelAttribute="buildingDTOSearch" id="listForm" action="/admin/building-list" method="GET">
                                <div class="row" >
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label class="name">
                                                    Tên tòa nhà
                                                </label>

                                                <form:input type="text" id="name" path="name"   class="col-xs-12 col-sm-6 form-control" />
                                            </div>
                                            <div class="col-xs-6">
                                                <label class="name">
                                                    Diện tích sàn
                                                </label>

                                                <form:input type="number" id="floorArea"  path="floorArea" class=" form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-2">
                                                <label class="name">
                                                    Quận hiện có
                                                </label>

                                                <form:select class=" form-control" id="district" path="district"
                                                        placeholder="--Chọn quận-- ">
                                                    <form:option value="">--Chọn quận-- </form:option>
                                                    <form:options items="${typeDistrict}" />
                                                </form:select>

                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name">
                                                    Phường
                                                </label>

                                                <form:input type="text" id="ward" path="ward" class=" form-control" />
                                            </div>
                                            <div class="col-xs-5">
                                                <label class="name">
                                                    Đường
                                                </label>

                                                <form:input type="text" id="street" path="street" class=" form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-4">
                                                <label class="name">
                                                    Số tầng hầm
                                                </label>

                                                <form:input type="number" id="numberOfBasement" path="numberOfBasement" class=" form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">
                                                    Hướng
                                                </label>

                                                <form:input type="text" id="direction" path="direction" class=" form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">
                                                    Hạng
                                                </label>

                                                <form:input type="number" id="level"  path="level" class=" form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-3">
                                                <label class="name">
                                                    Diện tích từ
                                                </label>

                                                <form:input type="number" id="areaFrom" path="areaFrom" class=" form-control" />
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">
                                                    Diện tích đến
                                                </label>

                                                <form:input type="number" id="areaTo" path="areaTo" class=" form-control" />
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">
                                                    Giá thuê từ
                                                </label>

                                                <form:input type="number" id="rentPriceFrom" path="rentPriceFrom" class=" form-control" />
                                            </div>
                                            <div class="col-xs-3">
                                                <label class="name">
                                                    Giá thuê đến
                                                </label>

                                                <form:input type="number" id="rentPriceTo" path="rentPriceTo" class=" form-control" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-4">
                                                <label class="name">
                                                    Tên quản lí
                                                </label>

                                                <form:input type="text" id="managerName" path="managerName" class=" form-control" />
                                            </div>
                                            <div class="col-xs-4">
                                                <label class="name">
                                                    Điện thoại quản lí
                                                </label>

                                                <form:input type="text" id="managerPhone" path="managerPhone" class=" form-control" />
                                            </div>

                                            <div class="col-xs-2">
                                                <security:authorize access="hasRole('MANAGER')" >
                                                    <label class="name">
                                                        Chọn nhân viên phụ trách
                                                    </label>

                                                    <form:select class="form-control" id="staffId" path="staffId"
                                                                 placeholder="--Chọn nhân viên-- ">
                                                        <form:option value="">--Chọn nhân viên-- </form:option>
                                                        <form:options items="${staffs}"/>



                                                    </form:select>
                                                </security:authorize>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-6">


                                                    <form:checkboxes items="${rentCode}" path="typeCode"  />


                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12" style="padding-top: 5px;">
                                            <div class="col-xs-6">
                                                <button style="align-items: center;" type="button" id="btnSearchBuilding"
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
                            <a href="/admin/building-edit">
                                <button class="btn btn-success" title="thêm tòa nhà">
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
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>

                            <th>Số tầng hầm</th>

                            <th>

                                Tên quản lí
                            </th>
                            <th>Số điện thoại</th>
                            <th>DT sàn</th>
                            <th>DT Trống</th>
                            <th>DT Thuê</th>
                            <th>Giá Thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>Thao tác</th>

                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="buildingList" items="${buildingLists}">
                        <tr>

                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" value=" ${buildingList.id}" class="ace">
                                        <span class="lbl"></span>
                                    </label>
                                </td>

                                <td>
                                   ${buildingList.name}
                                </td>
                                <td>${buildingList.address}</td>
                                <td >${buildingList.numberOfBasement}</td>
                                <td>${buildingList.managerName}</td>

                                <td >
                                        ${buildingList.managerPhone}
                                </td>
                                <td>${buildingList.floorArea}</td>
                                <td>${buildingList.emptyArea}</td>
                                <td>${buildingList.rentArea}</td>
                                <td>${buildingList.rentPrice}</td>
                                <td>${buildingList.serviceFee}</td>
                                <td>${buildingList.brokerageFee}</td>

                                <td>

                                    <div class="hidden-sm hidden-xs btn-group">
                                        <security:authorize access="hasRole('MANAGER')">
                                        <button onclick="assignmentBuilding(${buildingList.id})" title="Giao tòa nhà"
                                                class="btn btn-xs btn-success">

                                            <i class="ace-icon glyphicon glyphicon-list"></i>

                                        </button>
                                        </security:authorize>
                                        <a href="/admin/building-edit-${buildingList.id}"   title="Sửa tòa nhà" class="btn btn-xs btn-info">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>
                                        <security:authorize access="hasRole('MANAGER')">
                                        <button title="Xóa tòa nhà" onclick="btnDeleteOnly(${buildingList.id})" class="btn btn-xs btn-danger">
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


                </div><!-- /.span -->
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
                                           href="/admin/building-list?page=${currentPage - 1}"
                                           aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                </c:if>
                                <c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
                                    <li class="page-item">
                                        <a class="${ currentPage eq (loop.index +1) ? 'active page-link ' : 'page-link'}"
                                           href="/admin/building-list?page=${loop.index +1}">${loop.index +1}
                                        </a>
                                    </li>
                                </c:forEach>
                                <c:if test="${currentPage != totalPages}">
                                    <li class="page-item">
                                        <a
                                           href="/admin/building-list?page=${currentPage + 1}" aria-label="Next">
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
</div>
<!-- Modal -->
<div class="modal fade" id="assignmentBuildingModel" role="dialog">
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
                <input type="hidden" id="buildingId" name="buildingId" value="" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="loadStaff()">Giao tòa
                    nhà</button>
                <button type="button" class="btn btn-default" id="btnCancel">Đóng</button>
            </div>
        </div>

    </div>
</div>

    <script>
        $('#btnSearchBuilding').click(function (e){
            e.preventDefault();
            $('#listForm').submit();
        });

        $('#btnCancel').click(function (e){
          window.location.href ="/admin/building-list";

        })

        function assignmentBuilding(buildingId) {

            $('#buildingId').val(buildingId);
            var data = {};
            data['buildingId'] = buildingId;
            $.ajax({
                type: "POST",
                url: "http://localhost:8081/api/building/"+buildingId+"/staffs",
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
            $('#assignmentBuildingModel').modal();
        }

       function loadStaff (e) {

            var data = {};
            data['buildingId'] = $('#buildingId').val();
            var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function (index,item) {
                return item.value;
            }).get();
            data['staffs'] = staffs;
            $.ajax({
                type: "PUT",
                url: "http://localhost:8081/api/building/assignmentBuilding",
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

            window.location.href ="/admin/building-list";

       }

        function btnDeleteOnly( idBuilding ){
            jsonDelete(idBuilding)
            window.location.href ="/admin/building-list";
        }

        function btnDeleteAll() {

            window.location.href ="/admin/building-list";
            var idBuilding = $('#simple-table').find('tbody input[type=checkbox]:checked').map(function (index,item) {
                return item.value;
            }).get();


            jsonDelete(idBuilding)
        }

        function jsonDelete(idBuilding) {

            $.ajax({
                type: "DELETE",
                url: "http://localhost:8081/api/building/delete-"+idBuilding,
                // data: JSON.stringify(data),
                // contentType: "application/json",
                dataType: "JSON",
                success: function (response) {
                    console.log("success")
                },
                error: function (response) {
                    console.log("success")
                }
            })
        }







    </script>
</body>

</div>
<!--Js Model-->

<!--Js Model-->
</html>


