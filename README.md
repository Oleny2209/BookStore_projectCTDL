# Đồ án giữa kì môn Cấu Trúc Dữ liệu nhóm 31
## Tên đồ án: Ứng Dụng Quản Lí Bán Sách

## Hướng dẫn sử dụng
 - Chạy code tại ```src/view/MainFrame.java```
 - Chỉ cần clone repo về máy và thêm vào project sẵn có
# 1. Giới Thiệu
## 1.1 Mục Đích Đề Tài
Đề tài "Quản Lý Bán Sách" nhằm mục đích xây dựng một ứng dụng phần mềm hỗ trợ quản lý các thông tin liên quan đến sách, khách hàng và hóa đơn bán hàng tại các cửa hàng sách. Hệ thống giúp đơn giản hóa công việc quản lý, tiết kiệm thời gian và nâng cao hiệu quả làm việc.

## 1.2 Phạm Vi Đề Tài
<ol>
   <li> Quản lý thông tin sách: thêm, sửa, xóa sách và tìm kiếm sách theo nhiều tiêu chí.</li>
   <li> Quản lý thông tin khách hàng: lưu trữ thông tin cá nhân của khách hàng để phục vụ các chương trình chăm sóc khách hàng.</li>
   <li> Quản lý hóa đơn bán hàng: tạo, lưu trữ và tra cứu hóa đơn bán hàng.</li>
</ol>

## 1.3 Công Cụ Phát Triển

- Ngôn ngữ lập trình: Java
- Giao diện người dùng: Java Swing

# 2. Phân Tích Hệ Thống

## 2.1 Yêu Cầu Chức Năng

> Quản lý sách:
<ol>
 <li>Thêm mới sách vào hệ thống với các thông tin như mã sách, tên sách, thể loại, tác giả, nhà xuất bản, năm xuất bản, số lượng và giá.</li>
 <li>Chỉnh sửa hoặc xóa thông tin sách.</li>
 <li>Tìm kiếm sách dựa trên các tiêu chí: mã sách, tên sách, thể loại, tác giả, năm xuất bản.</li>
</ol>

> Quản lý khách hàng:

<ol>
 <li>Lưu trữ thông tin khách hàng bao gồm mã khách hàng, tên, số điện thoại, thứ hạng, tổng số tiền đã mua.</li>
 <li>Tìm kiếm, cập nhật và xóa thông tin khách hàng.</li>
</ol>

> Quản lý hóa đơn bán hàng:

<ol>
 <li>Tạo hóa đơn bán hàng với thông tin mã hóa đơn, khách hàng, sách bán, số lượng và tổng giá trị.</li>
 <li>Tra cứu hóa đơn theo mã hóa đơn hoặc thông tin khách hàng.</li>
</ol>

## 2.2 Các chức năng hỗ trợ
- FileLoader: dùng để đọc dữ liệu sách, khách hàng được lưu trong ```<namefile>.txt```.
- FileWriter: dùng để ghi lại các thay đổi trong quá trình hoạt động.
- AnalyzeDate: dùng để xử lí các trường hợp nhập sai định dạng ngày tháng.

## Thành viên:
<div style="align-items: center; display: flex; gap: 2%"><img src="https://avatars.githubusercontent.com/u/162453856?s=400&u=7522f67c34b968c07bd6ab268a589245189de299&v=4" alt="Just Github avt" height="30" > <a href=https://github.com/kleitusOleny>Oleny KleiTus</a> </div> 
<div style="align-items: center; display: flex; gap: 2%"><img src="https://avatars.githubusercontent.com/u/145794983?v=4" alt="Just Github avt" height="30" > <a href=https://github.com/JukisYuri>Jukis Yuri</a> </div> 
<div style="align-items: center; display: flex; gap: 2%"><img src="https://avatars.githubusercontent.com/u/163015310?v=4" alt="Just Github avt" height="30" > <a href=https://github.com/Background69>Background69</a> </div> 
<div style="align-items: center; display: flex; gap: 2%"><img src="https://avatars.githubusercontent.com/u/188865554?v=4" alt="Just Github avt" height="30" > <a href=https://github.com/MikeOlala>Mike</a> </div> 
