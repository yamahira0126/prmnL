//http://localhost:8080/SelectBudget
package com.example.license.page.budget;

import com.example.license.MySession;
import com.example.license.data.Budget;
import com.example.license.page.common.MainMenu;
import com.example.license.service.IBudgetService;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.text.SimpleDateFormat;

@MountPath("SelectBudget")
public class SelectBudget extends MainMenu {

    @SpringBean
    private IBudgetService budgetService;
    public  SelectBudget() {
        //bugdetのデータが入ったリストのモデルを用意
            //MySessionからAccountインスタンスを取り出す
        var budgetsModel = Model.ofList(budgetService.findBudgets(MySession.get().getAccount()));


        //ListView
        var budgetsLV = new ListView<>("budgets", budgetsModel) {
            //wicket:id="budget"の中身の設定
            @Override
            protected void populateItem(ListItem<Budget> listItem) {
                var itemModel = listItem.getModel();
                var budget = itemModel.getObject();
                //Date型のパターンを設定
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                var budgetIdModel = Model.of(budget.getBudgetId());
                var budgetIdLabel = new Label("budgetId", budgetIdModel);
                listItem.add(budgetIdLabel);

                var budgetNameModel = Model.of(budget.getBudgetName());
                var budgetNameLabel = new Label("budgetName", budgetNameModel);
                listItem.add(budgetNameLabel);

                var budgetStartDateModel = Model.of(formatter.format(budget.getBudgetStartDate()));
                var budgetStartDateLabel = new Label("budgetStartDate", budgetStartDateModel);
                listItem.add(budgetStartDateLabel);

                var budgetEndDateModel = Model.of(formatter.format(budget.getBudgetEndDate()));
                var budgetEndDateLabel = new Label("budgetEndDate", budgetEndDateModel);
                listItem.add(budgetEndDateLabel);

                var toUpdateBudget = new Link<>("toUpdateBudget") {
                    @Override
                    public void onClick() {
                        setResponsePage(new UpdateBudget(budget));
                    }
                };
                listItem.add(toUpdateBudget);

                var toDeleteBudget = new Link<>("toDeleteBudget") {
                    @Override
                    public void onClick() {
                        setResponsePage(new DeleteBudget(budget));
                    }
                };
                listItem.add(toDeleteBudget);

            }
        };
        add(budgetsLV);

        //MakeBudgetページに遷移する
        var toMakeBudget = new BookmarkablePageLink<>("toMakeBudget", MakeBudget.class);
        add(toMakeBudget);

    }
    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(SelectBudget.class, "../js/sort.js")));
        response.render(JavaScriptHeaderItem.forReference(new JavaScriptResourceReference(SelectBudget.class, "../js/paging.js")));
    }
}
