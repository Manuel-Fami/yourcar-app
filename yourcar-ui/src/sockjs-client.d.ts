// sockjs-client.d.ts
declare module 'sockjs-client/dist/sockjs' {
  // Nous disons à TypeScript que ce module existe
  var SockJS: any;
  export = SockJS; // Exporte SockJS en tant que module
}
