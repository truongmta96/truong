/** Cấu hình máy chủ phát triển: chuyển mọi yêu cầu /api sang Spring Boot cổng 8080, giúp giao diện và cookie dùng chung nguồn truy cập. */
import { defineConfig } from 'vite';
export default defineConfig({ server: { proxy: { '/api': 'http://127.0.0.1:8080' } } });
