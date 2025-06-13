# TuCheTao [![Discord](https://img.shields.io/discord/1353293624238145626.svg?label=\&logo=discord\&logoColor=ffffff\&color=7389D8\&labelColor=6A7EC2)](https://discord.typicalsmc.me/discord) ![Supported server version](https://img.shields.io/badge/minecraft-1.16%2B-green)

Ngôn ngữ: [Vietnamese](README_VN.md), **[English](README.md)**

Plugin tự động chế tạo vật phẩm khi người chơi có đủ nguyên liệu cần thiết.

**Hỗ trợ vật phẩm của:** Minecraft, MMOItems, ItemsAdder

![Bstats](https://bstats.org/signatures/bukkit/TuCheTao.svg)

# Tải xuống

- [Tải plugin tại đây](https://github.com/SimpMC-Studio/TuCheTao/releases/tag/1.0)

# Tính năng hiện có

* Tự động chế tạo nếu đủ nguyên liệu
* Hỗ trợ GUI danh sách vật phẩm có thể chế tạo
* Hiển thị thông tin chi tiết của vật phẩm trong menu
* Hỗ trợ quyền hạn để giảm thời gian chờ chế tạo
* Tự động hiển thị các công thức đang tồn tại trong server
* Hỗ trợ màu sắc `&` và cấu hình GUI dễ dàng

> 🎥 **Video demo GUI:** [Xem tại đây](https://youtu.be/vqMgTj9-Oos)

# Lệnh & Quyền

**Lệnh dành cho người chơi:**

| Lệnh         | Chức năng                                                                       | Permission           |
|--------------|---------------------------------------------------------------------------------|----------------------|
| /tuchetao    | Bật / tắt chế độ tự động chế tạo                                                | tuchetao.use         |
| /xemcongthuc | Mở menu danh sách công thức chế tạo (chỉ hiển thị công thức có quyền tương ứng) | tuchetao.xemcongthuc |

**Lệnh dành cho admin:**

| Lệnh            | Chức năng              | Permission            |
|-----------------|------------------------|-----------------------|
| /tuchetaoreload | Reload cấu hình plugin | tuchetao.admin.reload |

**Permission điều chỉnh thời gian chế tạo:**

| Permission                    | Mô tả                                                            |
|-------------------------------|------------------------------------------------------------------|
| tuchetao.timecraft.\<số giây> | Thời gian chờ chế tạo. Ví dụ: `tuchetao.timecraft.10` là 10 giây |

# Cấu hình plugin

Thư mục cấu hình mặc định: `./plugins/TuCheTao`

```
TuCheTao
│   config.yml
│   messages.yml
│
└───recipes
        example-recipe.yml
```

* Các công thức chế tạo đặt tại thư mục `recipes/`
* Giao diện và thiết lập thời gian chờ được cấu hình tại `config.yml`
* Tin nhắn và màu sắc có thể chỉnh trong `messages.yml`

# Ghi chú

* Plugin đang được nâng cấp để sử dụng hệ thống **Adventure API** và **MiniMessage** để tương thích tốt hơn với các
  phiên bản Paper hiện đại.

# Liên hệ

* Discord hỗ trợ: `typical.smc`
* Hoặc mở issue trên GitHub nếu có lỗi hoặc cần hỗ trợ thêm.