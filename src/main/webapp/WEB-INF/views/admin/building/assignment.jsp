<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/19/2025
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Danh sách nhân viên</h4>
    </div>
    <div class="modal-body">
        <form action="/api/building/assignmentBuildingAssigment" method="post">
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

                <c:forEach var="item" items="${dataStaffs}">

                    <tr>
                        <td class="text-center">
                            <input type="checkbox" value="${item.staffId}" name="staffIds" id="checkbox_ + ${item.staffId}" class="check-box-element" ${item.checked} />
                        </td>
                        <td class="text-center">${item.fullName}</td>
                    </tr>

                </c:forEach>



                </tbody>

            </table>
            <input type="hidden" id="buildingId" name="buildingId" value=${buildingId} />
            <div class="modal-footer">
                <button type="submit" class="btn btn-default" >Giao tòa
                    nhà</button>
                <button type="button" class="btn btn-default" id="btnCancel">Đóng</button>
            </div>
        </form>


    </div>

</div>
</body>
</html>
