import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import "./globals.css";
import { Toaster } from "@/components/ui/toaster";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: {
    default: "OpenTutor",
    template: "%s | OpenTutor",
  },
  description:
    "OpenTutor is an online learning platform offering interactive courses, expert guidance, and modern tools to enhance your knowledge and skills.",
  keywords: [
    "OpenTutor",
    "E-learning platform",
    "Online courses",
    "Student learning",
    "Education platform",
    "Interactive learning",
    "Skill development",
  ],
  authors: [{ name: "OpenTutor Team" }],
  creator: "OpenTutor",
  applicationName: "OpenTutor",

  icons: {
    icon: "/favicon.ico", // Replace with your own favicon in public folder
  },

  openGraph: {
    title: "OpenTutor - Learn Without Limits",
    description:
      "Join OpenTutor to access high-quality online courses, track your progress, and improve your skills anytime, anywhere.",
    url: "https://opentutor.com", // Replace with your actual domain
    siteName: "OpenTutor",
    type: "website",
  },

  twitter: {
    card: "summary_large_image",
    title: "OpenTutor - Online Learning Platform",
    description:
      "Interactive courses, expert tutors, and modern learning tools. Start your journey with OpenTutor today!",
  },
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased bg-background text-foreground`}
      >
        {children}
        <Toaster />
      </body>
    </html>
  );
}