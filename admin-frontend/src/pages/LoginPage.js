import React, { useState } from 'react';
import axios from 'axios';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');

  const handleLogin = async () => {
    try {
      const response = await axios.post('http://localhost:8000/api/login_admin', {  // <-- 여기 수정
        username: username,
        password: password
      });
      const token = response.data.token;
      localStorage.setItem('authToken', token);
      setMessage('로그인 성공!');
      // TODO: 다른 페이지로 이동 (예: dashboard)
    } catch (error) {
      console.error(error);
      setMessage('로그인 실패');
    }
  };

  return (
    <div>
      <h2>관리자 로그인</h2>
      <input
        type="text"
        placeholder="아이디"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      /><br />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      /><br />
      <button onClick={handleLogin}>로그인</button>
      <p>{message}</p>
    </div>
  );
}

export default LoginPage;
