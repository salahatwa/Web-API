// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
declare const require: any;
const { name, description, version } = require('../../package.json');

export const environment = {
  production: false,
  api: 'http://localhost:8080/api',
  unauth_api:'http://localhost:8080/unauth',
  name,
  description,
  version
};
