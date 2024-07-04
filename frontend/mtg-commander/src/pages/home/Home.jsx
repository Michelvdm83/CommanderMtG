import { useState } from "react";

export default function Home({ messages }) {
  return (
    <>
      {messages.map((message, index) => (
        <div key={index}>{message}</div>
      ))}
    </>
  );
}
