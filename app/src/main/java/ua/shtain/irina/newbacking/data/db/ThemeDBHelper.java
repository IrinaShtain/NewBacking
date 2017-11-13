package ua.shtain.irina.newbacking.data.db;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import ua.shtain.irina.newbacking.data.model.Author;
import ua.shtain.irina.newbacking.data.model.Author_Table;
import ua.shtain.irina.newbacking.data.model.GipsyOver100;
import ua.shtain.irina.newbacking.data.model.GipsyOver100_Table;
import ua.shtain.irina.newbacking.data.model.GipsyTill100;
import ua.shtain.irina.newbacking.data.model.GipsyTill100_Table;
import ua.shtain.irina.newbacking.data.model.PokerStrategyFrom50To500;
import ua.shtain.irina.newbacking.data.model.PokerStrategyFrom50To500_Table;
import ua.shtain.irina.newbacking.data.model.PokerStrategyLongTerm;
import ua.shtain.irina.newbacking.data.model.PokerStrategyLongTerm_Table;
import ua.shtain.irina.newbacking.data.model.PokerStrategyOver500;
import ua.shtain.irina.newbacking.data.model.PokerStrategyOver500_Table;
import ua.shtain.irina.newbacking.data.model.PokerStrategyTill50;
import ua.shtain.irina.newbacking.data.model.PokerStrategyTill50_Table;

/**
 * Created by Irina Shtain on 13.11.2017.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ThemeDBHelper {

    public void saveDataPSTill50(ArrayList<PokerStrategyTill50> list) {
        Delete.table(PokerStrategyTill50.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<PokerStrategyTill50>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public void saveDataPSFrom50Till500(ArrayList<PokerStrategyFrom50To500> list) {
        Delete.table(PokerStrategyFrom50To500.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<PokerStrategyFrom50To500>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public void saveDataPSOver500(ArrayList<PokerStrategyOver500> list) {
        Delete.table(PokerStrategyOver500.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<PokerStrategyOver500>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public void saveDataPSLongTerm(ArrayList<PokerStrategyLongTerm> list) {
        Delete.table(PokerStrategyLongTerm.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<PokerStrategyLongTerm>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public void saveDataGipsyTill100(ArrayList<GipsyTill100> list) {
        Delete.table(GipsyTill100.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<GipsyTill100>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public void saveDataGipsyOver100(ArrayList<GipsyOver100> list) {
        Delete.table(GipsyOver100.class);
        FlowManager.getDatabase(BackingDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<GipsyOver100>) (baseModel, wrapper) -> baseModel.save()).addAll(list).build())
                .error((transaction, error) -> {

                })
                .success(transaction -> {

                }).build().execute();
    }

    public boolean notExistRecord(PokerStrategyTill50 pokerStrategyTill50) {
        return SQLite.select()
                .from(PokerStrategyTill50.class)
                .where(PokerStrategyTill50_Table.title.is(pokerStrategyTill50.getTitle()))
                .querySingle() == null;
    }

    public boolean notExistRecord(PokerStrategyFrom50To500 pokerStrategyFrom50To500) {
        return SQLite.select()
                .from(PokerStrategyFrom50To500.class)
                .where(PokerStrategyFrom50To500_Table.title.is(pokerStrategyFrom50To500.getTitle()))
                .querySingle() == null;
    }

    public boolean notExistRecord(PokerStrategyOver500 pokerStrategyOver500) {
        return SQLite.select()
                .from(PokerStrategyOver500.class)
                .where(PokerStrategyOver500_Table.title.is(pokerStrategyOver500.getTitle()))
                .querySingle() == null;
    }

    public boolean notExistRecord(PokerStrategyLongTerm pokerStrategyLongTerm) {
        return SQLite.select()
                .from(PokerStrategyLongTerm.class)
                .where(PokerStrategyLongTerm_Table.title.is(pokerStrategyLongTerm.getTitle()))
                .querySingle() == null;
    }

    public boolean notExistRecord(GipsyTill100 gipsyTill100) {
        return SQLite.select()
                .from(GipsyTill100.class)
                .where(GipsyTill100_Table.title.is(gipsyTill100.getTitle()))
                .querySingle() == null;
    }

    public boolean notExistRecord(GipsyOver100 gipsyOver100) {
        return SQLite.select()
                .from(GipsyOver100.class)
                .where(GipsyOver100_Table.title.is(gipsyOver100.getTitle()))
                .querySingle() == null;
    }

    public boolean isAuthorInBlackList(String authorName) {
        return !SQLite.select()
                .from(Author.class)
                .where(Author_Table.userName.is(authorName))
                .queryList()
                .isEmpty();
    }

    public boolean existTablePSTill50() {
        return !SQLite.select()
                .from(PokerStrategyTill50.class)
                .queryList().isEmpty();
    }

    public boolean existTablePSFrom50To500() {
        return !SQLite.select()
                .from(PokerStrategyFrom50To500.class)
                .queryList().isEmpty();
    }

    public boolean existTablePSOver500() {
        return !SQLite.select()
                .from(PokerStrategyOver500.class)
                .queryList().isEmpty();
    }

    public boolean existTablePSLongTerm() {
        return !SQLite.select()
                .from(PokerStrategyLongTerm.class)
                .queryList().isEmpty();
    }

    public boolean existTableGipsyTill100() {
        return !SQLite.select()
                .from(GipsyTill100.class)
                .queryList().isEmpty();
    }

    public boolean existTableGipsyOver100() {
        return !SQLite.select()
                .from(GipsyOver100.class)
                .queryList().isEmpty();
    }
}
