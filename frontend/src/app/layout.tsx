import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import Header from "@/components/Header";
import Providers from "@/components/Providers";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "AI Social Platform",
  description: "An AI-First Social Platform",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased bg-gray-100 dark:bg-gray-900`}
      >
        <Providers>
          <div className="flex flex-col min-h-screen">
            <Header />
            <div className="flex flex-1">
              <aside className="w-64 bg-white dark:bg-gray-800 p-4 hidden md:block">
                <h2 className="text-lg font-bold">Side Menu</h2>
                {/* Side menu content goes here */}
              </aside>
              <main className="flex-1 p-4">{children}</main>
            </div>
          </div>
        </Providers>
      </body>
    </html>
  );
}
