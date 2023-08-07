import React, { useState } from "react";
import styles from './styles.module.css'

interface ILayoutProps {
  children: JSX.Element;
}

const Layout: React.FC<ILayoutProps> = ({
  children,
}) => {
  return (
    <div>
        <div className={styles.header}>
            header
        </div>
      <div>
        <main>{children}</main>
      </div>
    </div>
  );
};

export default Layout;
