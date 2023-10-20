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

  NewDataWithdate = (dateText, callback) => {
    let dateNum = Date.parse(`${dateText} 00:00:00`);
    if (isNaN(dateNum)) return;
    let date = new Date(dateNum);

    return firestore()
      .collection("cdate")
      .add({
        data: `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`,
      })
      .then((doc) => {
        console.log("ok" + doc.id);
        this.getdate(callback);
      })
      .catch((err) => {
        return console.log(err);
      });
  };

  getdate = (callback) => {
    return firestore()
      .collection("cdate")
      .where("data", "==", "2023-5-10")
      .get()
      .then((res) => {
        let dates = res.docs.map((doc) => {
          let data = doc.data();
          return { date: new Date(data.data).toDateString() };
        });
        return callback(dates);
      })
      .catch((err) => {
        return console.log(err);
      });
  };
}
