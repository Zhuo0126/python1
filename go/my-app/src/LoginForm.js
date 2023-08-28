import React, {  useState ,useEffect} from 'react';
import './LoginForm.css'; // 导入自定义的 CSS 样式
import { useNavigate } from 'react-router-dom';
import JSEncrypt from 'jsencrypt'
 
const LoginForm = () => {
  const [publicKey,setPublicKey] = useState('');

  const getPublicKey= ()=>{

    let url='http://localhost:8080/api/pubkey'
    fetch(url,{
        method:'GET',
        headers:{
          'Content-Type':'text/plain',
        },
        mode:'cors'
    }).then(res=>res.text()).then(data=>{
        console.log(data);
        setPublicKey(data);
    })
  }

  useEffect(() => {
    getPublicKey(); // 在组件渲染时调用 getPublicKey
  }, []);

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const nevigate =useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();
    //處理登錄表單提交邏輯
    
    const encrypt = new JSEncrypt()
    encrypt.setPublicKey(publicKey)

    const loginData = {
      username: username,
      password: encrypt.encrypt(password),
    };

    //發送port請求將數據傳遞給後端API
    fetch('http://localhost:8080/api/login',{
      method :'POST',
      headers:{
        'Content-Type':'application/json',
      },
      body :JSON.stringify(loginData),
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      if (data.success) {
        nevigate('/menu');
      } else {
        
      }
    })
    .catch((error) => {
      console.error('請求出錯:',error)
    });
  };

  return (
    <div className="main-container">
      <div className="left-section">
    <form className="custom-body reservation-form1" onSubmit={handleSubmit}>
      <h1 className="title">HOME(主頁)</h1>
      <h2>用戶登錄</h2>
      <label>
        用戶名：
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      </label>
      <br />
      <label>
        密碼：
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </label>
      <br />
      <button type="submit">登錄</button>
    </form>
    </div>
    <div className="right-section">
    <form className="custom-body reservation-form1" onSubmit={handleSubmit}>
      <h1 className="title">HOME2(主頁)</h1>
      <h2>用戶註冊</h2>
      <label>
        用戶名：
        <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
      </label>
      <br />
      <label>
        密碼：
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      </label>
      <br />
      <button type="submit">註冊</button>
    </form>
    </div>
    </div>
  );
};

export default LoginForm;