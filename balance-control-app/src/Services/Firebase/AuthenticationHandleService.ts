import auth from "@react-native-firebase/auth";
import { Alert } from "react-native";

export class AuthenticationHandleService {
  SignInOrRegister = (userName, password) => {
    let threatResult = "";
    auth()
      .createUserWithEmailAndPassword(userName, password)
      .then(() => {})
      .catch((err) => {
        threatResult = this.ThreatCreateUserCatch(err, userName, password);
        if (threatResult != "") Alert.alert(threatResult);
      });
  };

  ThreatCreateUserCatch = (err, userName, password) => {
    let result = "";
    if (err.code)
      switch (err.code) {
        case "auth/email-already-in-use":
          auth()
            .signInWithEmailAndPassword(userName, password)
            .then(() => {})
            .catch((err) => {
              let threatResult = this.ThreatSignInCatch(err);
              if (threatResult != "") Alert.alert(threatResult);
            });
          break;
        default:
          result = this.CommonCatchAuthentication(err);
          break;
      }
    else result = "Erro ao registrar o usuario";
    return result;
  };

  ThreatSignInCatch = (err) => {
    let result = '"Erro ao registrar o usuario"';
    if (err.code)
      switch (err.code) {
        case "auth/invalid-login":
          result = "Credenciais Inválidas.";
          break;
        default:
          result = this.CommonCatchAuthentication(err);
          break;
      }

    return result;
  };

  CommonCatchAuthentication = (err) => {
    let result = "";
    if (err.code) {
      switch (err.code) {
        case "auth/too-many-requests":
          result = "Aguarde, excesso de tentativas no login";
          break;
        default:
          result = `${err.code} ${err.message}`;
          break;
      }
    }
    return result;
  };
}
