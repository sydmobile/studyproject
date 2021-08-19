package com.study.study_module.fragment.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.R;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * <p>
 * date: 2020/7/13 15:50
 *
 * @author syd
 * @version 1.0
 */
public class EditNameDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.activity_fragment_dialog,container,false);
        return view;
    }

    public static void main(String[] args) {
        String broadcast ="1111FFFF2222eeeeAAAACCCCBBBB5555";

        String regex = "(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})(.{4})";
        boolean b = Pattern.matches(regex, broadcast);
        System.out.println(regex);
        broadcast = broadcast.replaceAll(regex, "$1-$2-$3-$4-$5-$6-$7-");
        System.out.println(broadcast);
    }
}
