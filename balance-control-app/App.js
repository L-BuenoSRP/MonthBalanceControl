import { StatusBar } from "expo-status-bar";
import { Pressable, StyleSheet, Text, View } from "react-native";
import database from "@react-native-firebase/database";
import React, { useState, useEffect } from "react";
import { AuthenticationHandleService } from "./Services/Firebase/AuthenticationHandleService";
import auth from "@react-native-firebase/auth";
import { DataBaseCartoesHandleService } from "./Services/Firebase/DatabaseCartoesHandleService";

export default function App() {
  // method();
  let authenticationHandleService = new AuthenticationHandleService();
let s = Document
  // Set an initializing state whilst Firebase connects
  const [initializing, setInitializing] = useState(true);
  const [user, setUser] = useState();

  // Handle user state changes
  function onAuthStateChanged(user) {
    setUser(user);
    if (initializing) setInitializing(false);
  }

  function logout() {
    auth()
      .signOut()
      .then(() => {
        console.log("deslogou");
      });
  }

  useEffect(() => {
    const subscriber = auth().onAuthStateChanged(onAuthStateChanged);
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

  new DataBaseCartoesHandleService().GetData();
  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Pressable onPress={() => logout()}>
        <Text>Deslogar</Text>
      </Pressable>
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
  },
});

const method = () => {
  database()
    .ref("/users/123b")
    .push({
      name: "Ada Lovelace",
      age: 31,
    })
    .then((s) => console.log("Data set." + s));
};
