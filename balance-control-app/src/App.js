import { StatusBar } from "expo-status-bar";
import {
  FlatList,
  Pressable,
  StyleSheet,
  Text,
  TextInput,
  View,
} from "react-native";
import database from "@react-native-firebase/database";
import React, { useState, useEffect } from "react";
import auth from "@react-native-firebase/auth";
// import { initializeApp } from "firebase/app";
import { AuthenticationHandleService } from "./Services/Firebase/AuthenticationHandleService";
import { DataBaseCartoesHandleService } from "./Services/Firebase/DatabaseCartoesHandleService";

export default function App() {
  // method();
  let authenticationHandleService = new AuthenticationHandleService();
  let dataBaseCartoesHandleService = new DataBaseCartoesHandleService();
  // Set an initializing state whilst Firebase connects
  const [initializing, setInitializing] = useState(true);
  const [user, setUser] = useState();
  const [text, setText] = useState("");
  const [datas, setDatas] = useState([]);
  // Handle user state changes
  function onAuthStateChanged(user) {
    setUser(user);
    if (initializing) setInitializing(false);
    setDatas(dataBaseCartoesHandleService.getdate(setDatas))
  }

  const firebaseConfig = {
    apiKey: "AIzaSyAjLlJIjXA91SDlaEliVrXk9D87XkeYi3s",
    authDomain: "f-balance-control.firebaseapp.com",
    databaseURL: "https://f-balance-control-default-rtdb.firebaseio.com",
    projectId: "f-balance-control",
    storageBucket: "f-balance-control.appspot.com",
    messagingSenderId: "733039487780",
    appId: "1:733039487780:web:edd6d22439be5d3f8aa1b1",
    measurementId: "G-8NH4ZDCX8B"
  };

  function logout() {
    auth()
      .signOut()
      .then(() => {
        console.log("deslogou");
      });
  }

  useEffect(() => {
    // initializeApp(firebaseConfig);r
    const subscriber = auth().onAuthStateChanged(onAuthStateChanged);

    dataBaseCartoesHandleService.GetData()

    return subscriber; // unsubscribe on unmount
  }, []);

  if (initializing) return null;

  if (!user) {
    return (
      <View>
        <Text>Login</Text>
        <Pressable
          onPress={() =>
            authenticationHandleService.SignInOrRegister(
              "leandro@bueno.com",
              "123456s"
            )
          }
        >
          <Text>Logar</Text>
        </Pressable>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Pressable onPress={() => logout()}>
        <Text>Deslogar</Text>
      </Pressable>
      <TextInput
        style={{ height: 40 }}
        placeholder="Type here to translate!"
        onChangeText={(newText) => setText(newText)}
        defaultValue={text}
      />
      <Pressable
        onPress={() =>
          dataBaseCartoesHandleService.NewDataWithdate(text, setDatas)
        }
      >
        <Text>Adicionar</Text>
      </Pressable>

      <FlatList
        data={datas}
        renderItem={({ item }) => <Text style={styles.item}>{item.date}</Text>}
      />
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
    paddingTop: 22,
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  }
});
