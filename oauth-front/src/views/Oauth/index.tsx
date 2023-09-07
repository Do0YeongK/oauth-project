import { useParams, useNavigate } from 'react-router-dom';
import React, { useEffect } from 'react'
import { useCookies } from 'react-cookie';

export default function Oauth() {

  const { token } = useParams();
  const [cookies, setCookie] = useCookies();
  const navigator = useNavigate();

  useEffect(() => {
    if (!token) return;
    setCookie('token', token, { path: '/' });
    navigator('/');
  },[token]);

  return (
    <div>Oauth : {token}</div>
  )
}
