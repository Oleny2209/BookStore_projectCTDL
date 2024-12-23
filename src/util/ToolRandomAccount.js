function generateRandomRow() {
    const id = generateRandomID();
    const name = generateRandomVietnameseName(); // Tạo họ và tên ngẫu nhiên
    const number = generateRandomBirthday();
    const type = generateRandomType(); // Random giữa VIP và Thường
    const phone = generateRandomPhoneNumber();
    const status = "0"; // Mặc định là 0

    return `${id} | ${name} | ${number} | ${type} | ${phone} | ${status}`;
}

// Hàm tạo ID ngẫu nhiên (1 chữ cái và 3 chữ số)
function generateRandomID() {
    const letter = String.fromCharCode(65 + Math.floor(Math.random() * 26)); // Chữ cái từ A-Z
    const digits = Math.floor(Math.random() * 1000); // 3 chữ số (0-999)
    return `${letter}${digits.toString().padStart(3, "0")}`;
}

// Hàm tạo ngày sinh nhật
function generateRandomBirthday() {
    const day = Math.floor(Math.random() * 31) + 1; // Số ngày từ 1 đến 31
    const month = Math.floor(Math.random() * 12) + 1; // Số tháng từ 1 đến 12
    const year = Math.floor(Math.random() * 10) + 2000; // Năm từ 2000 đến 2005

    const dayString = day.toString().padStart(2, "0"); // Đảm bảo 2 chữ số
    const monthString = month.toString().padStart(2, "0"); // Đảm bảo 2 chữ số

    return `${dayString}-${monthString}-${year}`;
}

// Hàm tạo số điện thoại ngẫu nhiên (10 chữ số bắt đầu bằng 0)
function generateRandomPhoneNumber() {
    const number = Math.floor(Math.random() * 900000000) + 100000000; // 9 chữ số tiếp theo (100000000-999999999)
    return `0${number}`;
}

// Hàm tạo giá trị ngẫu nhiên giữa "VIP" và "Thường"
function generateRandomType() {
    return Math.random() < 0.5 ? "VIP" : "Thường"; // 50% xác suất cho mỗi giá trị
}

// Hàm tạo tên Việt Nam ngẫu nhiên
function generateRandomVietnameseName() {
    const ho = ["Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Phan", "Vũ", "Đặng", "Bùi", "Đỗ", "Võ"];
    const tenDem = ["Văn", "Thị", "Hữu", "Ngọc", "Thanh", "Minh", "Đức", "Thảo", "Anh", "Tấn", "Thu"];
    const ten = ["Hùng", "Trang", "Linh", "Tuấn", "Hoa", "Hương", "Quân", "Dũng", "Lan", "Mai", "Hào", "Thông"];

    const randomHo = ho[Math.floor(Math.random() * ho.length)];
    const randomTenDem = tenDem[Math.floor(Math.random() * tenDem.length)];
    const randomTen = ten[Math.floor(Math.random() * ten.length)];

    return `${randomHo} ${randomTenDem} ${randomTen}`;
}

// Tạo nhiều dòng dữ liệu
function generateRandomData(rows = 10) {
    const data = [];
    for (let i = 0; i < rows; i++) {
        data.push(generateRandomRow());
    }
    return data;
}

// Số lượng dòng cần tạo
const randomData = generateRandomData(50);

// Hiển thị kết quả
console.log(randomData.join("\n"));
