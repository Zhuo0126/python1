const express = require('express');
const app = express();
const bodyParser = require('body-parser');
// const ReservationForm =require('./ReservationForm');
const cors = require('cors'); // 引入cors

app.use(bodyParser.json());
app.use(cors());

//處理登錄請求
app.post('/api/login', (req, res) => {
  const { username, password } = req.body;


  if (username === 'admin' && password === 'password') {
    res.json({ success: true, message: '登錄成功' });
  } else {
    res.json({ success: false, message: '用戶名或密碼錯誤' });
  }
});

// 啟動Service監聽Port=3001
const port = 3001;
app.listen(port, () => {
  console.log(`服務器運行在 http://localhost:${port}`);
});