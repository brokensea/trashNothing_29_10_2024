import React from 'react';
import Navigate from '../Components/Navigate';
import Header from '../Components/Header';
import Footer_1 from '../Components/Footer_1';
import Footer_2 from '../Components/Footer_2';
import { Outlet } from 'react-router-dom';
const Layout = () => {
    return (
        <div>
            <Navigate />
            <Header />
            <main>
                <Outlet /> 
            </main>
            <Footer_1 />
            <Footer_2 />
        </div>
    );
};

export default Layout;