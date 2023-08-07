import React, { ReactNode } from "react";
import type { AppProps } from "next/app";
import { NextPage } from "next";
import "../styles/globals.css"
import { Manrope } from "next/font/google";
import Layout from "@/layouts/Layout";

const manRope = Manrope({
  weight: ["300", "400", "500", "600", "700", "800"],
  display: "swap",
  subsets: ["cyrillic", "latin"],
  variable: "--font-manrope",
});

type PageProps = {
  initialStore: any;
  messages: any;
};

export type NextPageWithLayout<P = {}> = NextPage<P & PageProps> & {
  getLayout?: (page: any) => ReactNode;
};

type AppPropsWithLayout = AppProps & {
  Component: NextPageWithLayout;
  pageProps: PageProps;
};

export default function App({ Component, pageProps }: AppPropsWithLayout) {
  return (
      <>
        <Layout>
          <Component {...pageProps} />
        </Layout>
      </>
  );
}
