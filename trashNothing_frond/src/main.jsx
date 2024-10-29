import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

import { RouterProvider, createBrowserRouter } from 'react-router-dom'



const router = createBrowserRouter([
  {
    path: "/",
    element: <App />
  },
  // {
  //   path: "/login",
  //   element: <LoginPage />
  // },
  // {
  //   path: "/registration",
  //   element: <RegistrationPage />
  // },
  
  // {
  //   path: "/user",
  //   element: <ProtectedRoute element={<UserPage />} />
  // }
  
  
])
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
