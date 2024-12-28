<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/15/2024
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                <li class="active"><a href="/admin/building-list">Quản lí tòa nhà</a></li>
                <li class="active">
                    <i class="ace-icon"></i>Thêm tòa nhà</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thêm Tòa Nhà

                </h1>


            </div><!-- /.page-header -->

            <!--Begin form-->
            <div class="row " style="font-family: 'Times New Roman', Times, serif;">
                <form:form modelAttribute="buildingModel"  action="/admin/building-edit" id="form-edit" method="POST" enctype="multipart/form-data">
                    <form class="form-horizontal" role="form" style="margin-bottom: 40px;">

                        <div class="col-xs-12">
                            <c:if test="${not empty errorSQL}">
                                <div class="alert alert-danger">${errorSQL}</div>
                            </c:if>

                            <div class=" row form-group ">
                                <label class="col-sm-3">Tên tòa nhà</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorName">
                                        <form:errors path="name" cssClass="text-danger" />
                                    </c:set>

                                    <form:input type="text"  class="form-control" id="name" path="name" style="${not empty errorName ? 'border: 1px solid red;' : ''}" />
                                        ${errorName}
                                </div>


                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Quận</label>
                                <div class="col-sm-3 ">
                                    <c:set var="errorDistrict">
                                        <form:errors path="district" cssClass="text-danger" />
                                    </c:set>
                                    <form:select class=" form-control" id="district" path="district" style="${not empty errorDistrict ? 'border: 1px solid red;' : ''}"
                                                 placeholder="--Chọn quận-- " >
                                        <form:option value="">--Chọn quận-- </form:option>
                                        <form:options items="${typeDistrict}" />
                                    </form:select>
                                        ${errorName}
                                </div>


                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phường</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorWard">
                                        <form:errors path="ward" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="text" style="${not empty errorWard ? 'border: 1px solid red;' : ''}" id="ward" path="ward" class="form-control"/>
                                        ${errorWard}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Đường</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorStreet">
                                        <form:errors path="street" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="text" id="street" path="street"  class="form-control" style="${not empty errorName ? 'border: 1px solid red;' : ''}"/>
                                     ${errorStreet}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Kết cấu</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="structure" path="structure" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Số tầng hầm</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorNumberOfBasement">
                                        <form:errors path="numberOfBasement" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="number" style="${not empty errorNumberOfBasement ? 'border: 1px solid red;' : ''}" id="numberOfBasement" path="numberOfBasement"
                                           class="form-control"/>
                                        ${errorNumberOfBasement}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Diện tích sàn</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorFloorArea">
                                        <form:errors path="floorArea" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="number" id="floorArea" path="floorArea"
                                                style="${not empty errorFloorArea ? 'border: 1px solid red;' : ''}"
                                           class="form-control"/>
                                        ${errorFloorArea}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Hướng</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="direction" path="direction" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Hạng</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="level" path="level" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Diện tích thuê</label>

                                <div class="col-sm-9 ">
                                    <c:set var="errorRentArea">
                                        <form:errors path="rentArea" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="text" id="rentArea" path="rentArea"
                                                style="${not empty errorRentArea ? 'border: 1px solid red;' : ''}"
                                                class="form-control"/>
                                        ${errorFloorArea}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Giá thuê</label>
                                <div class="col-sm-9 ">
                                    <form:input type="number" id="rentPrice" path="rentPrice" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Mô tả giá</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="rentPriceDescription" path="rentPriceDescription" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phí dịch vụ</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="serviceFee" path="serviceFee" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phí ô tô</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="carFee" path="carFee" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phí mô tô</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="motoFee" path="motoFee" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phí ngoài giờ</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="overtimeFee" path="overtimeFee" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Tiền điện</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="electricityFee" path="electricityFee" class="form-control"/>
                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Đặt cọc</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="deposit" path="deposit" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Thanh toán</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="payment" path="payment" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Thời hạn thuê</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="rentTime" path="rentTime" class="form-control"/>
                                </div>

                            </div>

                        </div>

                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Thời gian trang trí</label>
                                <div class="col-sm-9 ">
                                    <form:input type="text" id="decorationTime" path="decorationTime" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Tên quản lí</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorManagerName">
                                        <form:errors path="managerPhone" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="text" id="managerName" path="managerName" class="form-control"  style="${not empty errorManagerName ? 'border: 1px solid red;' : ''}"/>
                                    ${errorManagerName}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">SĐT quản lí</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorManagerPhone">
                                        <form:errors path="managerPhone" cssClass="text-danger" />
                                    </c:set>
                                    <form:input type="text" id="managerPhone" path="managerPhone" class="form-control"
                                                style="${not empty errorManagerPhone ? 'border: 1px solid red;' : ''}"
                                        />
                                        ${errorManagerPhone}
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Phí môi giới</label>
                                <div class="col-sm-9 ">
                                    <form:input type="number" id="brokerageFee" path="brokerageFee" class="form-control"/>
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Loại tòa nhà</label>
                                <div class="col-sm-9 ">
                                    <c:set var="errorTypeCode">
                                        <form:errors path="typeCode" cssClass="text-danger" />
                                    </c:set>
                                    <form:checkboxes items="${rentCode}" path="typeCode" style="${not empty errorTypeCode ? 'border: 1px solid red;' : ''}"  />
                                    ${errorTypeCode}


                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Ghi chú</label>
                                <div class="col-sm-9 ">
                                    <form:input class="form-control" id="note" path="note" />
                                </div>

                            </div>

                        </div>
                        <div class="col-xs-12">
                            <div class=" row form-group ">
                                <label class="col-sm-3">Avatar</label>
                                <div class="col-sm-3 ">
                                    <input  type="file" id="avatarFile"
                                           accept=".png, .jpg, .jpeg" name="imageFile">
                                </div>
                                <div class="col-sm-3 ">
                                    <img src="/ImageMain/building/${buildingModel.imageName}"
                                             style="max-height: 250px;" alt="avatar preview"
<%--
                                         id="avatarPreview" />
                                </div>

                            </div>

                        </div>



                        <div class="col-xs-12">
                            <div class=" row">
                                <div class="col-sm-9 pull-right">
                                    <c:choose>
                                        <c:when test="${buildingModel.id == null}">
                                            <button class="btn btn-primary" id="btnAddBuilding" type="submit" >Thêm tòa nhà</button>
                                            <button class="btn btn-primary" id="btnCancel">Hủy thao tác</button>
                                        </c:when>
                                        <c:when test="${buildingModel.id != null}">
                                            <button class="btn btn-info" id="btnAddBuilding" type="submit" >Cập nhật tòa nhà</button>
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
<script>
    //upload image
    $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
            const imgURL = URL.createObjectURL(e.target.files[0]);
            $("#avatarPreview").attr("src", imgURL);
            $("#avatarPreview").css({ "display": "block" });
        });
    });

    // function btnCreate() {
    //     var data = {};
    //     var typeCode = [];
    //
    //     $('#form-edit').serializeArray().forEach(function (item) {
    //         if (item.name !== 'typeCode') {
    //             data[item.name] = item.value;
    //         } else {
    //             typeCode.push(item.value);
    //         }
    //     });
    //
    //     data['typeCode'] = typeCode;
    //     var imageFile = $('#avatarFile')[0].files[0];
    //
    //     var formData = new FormData();
    //     formData.append('buildingDTO', new Blob([JSON.stringify(data)], { type: 'application/json' }));
    //     formData.append('imageFile', imageFile);
    //
    //     $.ajax({
    //         type: "PUT",
    //         url: "http://localhost:8081/api/building/create",
    //         data: formData,
    //         processData: false,
    //         contentType: false,
    //
    //     });
    // }


    $('#btnCancel').click(function (e){
        window.location.href ="/admin/building-list";

    })

    // function btnUpdate(){
    //
    //     var data = {}
    //     var typeCode = []
    //     var formFields  = $('#form-edit').serializeArray();
    //     $.each(formFields , function (i, v) {
    //         if (v.name !== 'typeCode') {
    //             data["" + v.name + ""] = v.value;
    //         } else {
    //             typeCode.push(v.value);
    //         }
    //
    //     });
    //     data['typeCode'] = typeCode;
    //
    //
    //     $.ajax({
    //         type: "PUT",
    //         url: "http://localhost:8081/api/building/update",
    //         data: JSON.stringify(data),
    //         contentType: "application/json",
    //         dataType: "JSON",
    //         success: function (respond) {
    //             console.log(respond)
    //         },
    //         error: function (respond) {
    //             console.log(respond)
    //         }
    //     });
    // }



</script>
</body>
</html>
