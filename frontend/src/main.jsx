/**
 * Điểm khởi động React: tìm phần tử root trong index.html, gắn component App và nạp giao diện CSS dùng chung.
 */
import React from 'react';
import {createRoot} from 'react-dom/client';
import App from './App.jsx';
import './styles.css';
createRoot(document.getElementById('root')).render(<App/>);
