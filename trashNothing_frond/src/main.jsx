import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

import { RouterProvider, createBrowserRouter } from 'react-router-dom'
import LoginPage from './Pages/LoginPage.jsx'
import HomePage from './Pages/HomePage.jsx'
import Marktplatz from './Pages/Marktplatz.jsx'
import Product_CreatePage from './Pages/Product_CreatePage.jsx'
import ProductDetailsPage from './Pages/ProductDetailsPage.jsx'
import ProductSoldPage from './Pages/ProductSoldPage.jsx'
import RegisterPage from './Pages/RegisterPage.jsx'
import WishListPage from './Pages/WishListPage.jsx'
import Layout from './Pages/Layout.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        index: true,
        element: <HomePage />
      },
      {
        path: "/login",
        element: <LoginPage />
      },
      {
        path: "/registration",
        element: <RegisterPage />
      },
      {
        path: "/marktplatz",
        element: <Marktplatz />
      },
      {
        path: "/createproduct",
        element: <Product_CreatePage />
      },
      {
        path: "/detailsproduct",
        element: <ProductDetailsPage />
      },
      {
        path: "/soldproduct",
        element: <ProductSoldPage />
      },
      {
        path: "/wischlist",
        element: <WishListPage />
      }]
  }
]);
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)
