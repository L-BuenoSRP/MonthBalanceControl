import database from "@react-native-firebase/database";
import firestore, {
  FirebaseFirestoreTypes,
} from "@react-native-firebase/firestore";

export class DataBaseCartoesHandleService {
  //https://dev.to/rossanodan/how-to-fetch-subcollections-from-cloud-firestore-with-react-3n93
  //testar instrução de fetch com os dados, conforme usos do link acima, verificar se é viavel usar esse codigo
  // const fetchedMovie = {
  //     id: document.id,
  //     ...document.data()
  //   };
  GetData = () => {
    firestore()
      .collection("c1")
      .get()
      .then((querySnapshot) => {
        console.log("Total users: ", querySnapshot.size);

        querySnapshot.forEach((documentSnapshot) => {
          let f2 =
            documentSnapshot.get<FirebaseFirestoreTypes.DocumentReference>(
              "f2"
            );

          if (f2) {
            console.log(f2);
            f2.get().then((doc) => {
              console.log(doc);
            });
          }
          console.log(
            "User ID: ",
            documentSnapshot.id,
            documentSnapshot.data()
          );
        });
      });
  };
}
