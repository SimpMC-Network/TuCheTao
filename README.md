# TuCheTao

**Server Version**: Paper > 1.16
---

## 📌 Giới thiệu

TuCheTao là plugin cho phép **tự động chế tạo vật phẩm** khi người chơi có đủ nguyên liệu yêu cầu.  
Các tính năng nổi bật*:*

- Hỗ trợ **Permission** để giảm thời gian chờ chế tạo.
- GUI hiển thị danh sách vật phẩm có thể chế tạo.
- Video demo GUI: [Xem tại đây](https://youtu.be/vqMgTj9-Oos)

**Tính năng GUI:**

- Nút chuyển trang.
- Tự động hiện các công thức chế tạo hiện có trong server.
- Khi bấm vào vật phẩm, hiển thị menu con với thông tin chi tiết.

---

## ⚙️ Lệnh & Quyền

### 🎮 Dành cho Member:

| Lệnh           | Mô tả                                                                       |
|----------------|-----------------------------------------------------------------------------|
| `/tuchetao`    | Bật / tắt tính năng tự động chế tạo.                                        |
| `/xemcongthuc` | Mở GUI danh sách công thức chế tạo. (Chỉ hiện công thức có quyền tương ứng) |

**Quyền:**

- `tuchetao.use` – sử dụng lệnh `/tuchetao`
- `tuchetao.xemcongthuc` – sử dụng lệnh `/xemcongthuc`

---

### 🛠️ Dành cho Admin:

| Lệnh              | Mô tả                   |
|-------------------|-------------------------|
| `/tuchetaoreload` | Reload cấu hình plugin. |

**Quyền:**

- `tuchetao.admin.reload`

---

### ⏱️ Quyền chung:

- `tuchetao.timecraft.<giây>` – Quy định thời gian chế tạo cho từng người chơi (mặc định là `15` giây).  
  Ví dụ: `tuchetao.timecraft.10` sẽ giảm thời gian chờ còn 10 giây.

---

## 🔍 Chi tiết & Giải thích

- Hỗ trợ nguyên liệu là các item trong **Minecraft**, **MMOItems**, **ItemsAdder**
- Hỗ trợ vật phẩm đầu ra là item trong **Minecraft**, **MMOItems**, **ItemsAdder**
- Hỗ trợ tùy chỉnh GUI trong file config tương ứng.
- Hỗ trợ định dạng màu sắc Minecraft kiểu `&`.

---

## 📌 Ghi chú

- Plugin đang trong quá trình chuyển đổi toàn bộ tin nhắn sang MiniMessage và hệ thống `Adventure` để tương thích tốt
  hơn với các phiên bản Paper hiện đại.

---

## 📫 Liên hệ

Nếu có vấn đề hoặc cần hỗ trợ, hãy liên hệ discord `typical.smc` hoặc mở issue trên GitHub (nếu có).
